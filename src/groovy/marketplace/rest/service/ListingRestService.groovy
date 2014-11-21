package marketplace.rest.service

import javax.annotation.security.RolesAllowed

import org.hibernate.SessionFactory
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.codehaus.groovy.grails.commons.GrailsApplication
import marketplace.Listing
import marketplace.Agency
import marketplace.Profile
import marketplace.ListingActivity
import marketplace.Constants
import marketplace.ApprovalStatus
import marketplace.RejectionListing
import marketplace.ListingSnapshot
import marketplace.validator.ListingValidator
import org.springframework.security.access.AccessDeniedException
import org.springframework.transaction.annotation.Transactional

import marketplace.rest.representation.in.InputRepresentation

@Service
class ListingRestService extends RestService<Listing> {
    @Autowired ProfileRestService profileRestService
    @Autowired ListingActivityInternalService listingActivityInternalService
    @Autowired SessionFactory sessionFactory

    @Autowired
    public ListingRestService(GrailsApplication grailsApplication,
            ListingValidator listingValidator) {
        super(grailsApplication, Listing.class, listingValidator, null)
    }

    //needed for CGLIB
    ListingRestService() {}

    @Transactional(readOnly=true)
    public Set<Listing> getAllByAuthorId(Long profileId) {
        Profile profile = profileRestService.getById(profileId)
        Listing.findAllByAuthor(profile).grep { canView(it) } as Set
    }

    /**
     * Recursively find all Listings that are required by this one
     */
    @Transactional(readOnly=true)
    public Set<Listing> getAllRequiredListingsByParentId(Long id) {
        Listing parent = getById(id)

        getAllRequiredListings(parent, [parent] as Set).grep { canView(it) }
    }

    /**
     * Find all Listings that require this one. This is not recursive
     */
    @Transactional(readOnly=true)
    public Set<Listing> getRequiringListingsByChildId(Long id) {
        //ensure that they are allowed to view the child
        Listing child = getById(id)

        Listing.createCriteria().list() {
            required {
                eq('id', id)
            }

            ne('id', id)
        }.grep{ canView(it) }
    }

    /**
     * @param ignore Listings not to recurse into. Necessary to prevent infinite recursion
     */
    private Set<Listing> getAllRequiredListings(Listing parent,
            Set<Listing> ignore) {

        Set<Listing> immediateRequired = parent.required - ignore

        Set<Listing> recursiveRequired = immediateRequired.collect {
            getAllRequiredListings(it, (immediateRequired + ignore + parent))
        }.flatten()

        //get all immediate required items, items that those items require, and ignore the
        //current item
        immediateRequired + recursiveRequired - parent
    }

    @Override
    public void deleteById(Long id) {
        Listing item = getById(id)

        updateRelationshipsForDelete(item)

        //this is necessary because ApplicationLibraryEntry
        //belongsTo both ServiceItem and Profile.  At least
        //one side of the belongsTo must be explicitly broken
        item.applicationLibraryEntries.each {
            it.owner.removeFromApplicationLibrary(it)
        }
        item.applicationLibraryEntries.clear()

        super.deleteById(id)
    }

    /**
     * Get all Listings that match the passed-in parameters.
     * The different filters are combined using AND.
     * @param The organization to filter by.  For those with ROLE_ORG_STEWARD, this must be an
     * org that they are a steward of.  Can be null to match all orgs (or all orgs an Org Steward
     * is steward of).
     * @param approvalStatus The approvalStatus to filter by.  null to match all approvalStatuses
     * @param enabled True to match only enabled listings. false to match only disabled listings.
     * null to match all listings
     *
     * TODO add org steward stuff once that branch is merged in
     */
    @Override
    @RolesAllowed(['ROLE_ADMIN', 'ROLE_ORG_STEWARD'])
    public Set<Listing> getAllMatchingParams(
            InputRepresentation<Agency> org,
            ApprovalStatus approvalStatus,
            Boolean enabled,
            Integer offset, Integer max) {
        Agency ag = org ? RestService.getFromDb(org) : null

        //if (ag) {
            //profileRestService.checkOrgSteward(ag)
        //}

        Listing.createCriteria().list(max: max, offset: offset) {
            if (ag) {
                agency {
                    eq('id', ag.id)
                }
            }
            //else if (profileRestService.isOrgSteward()) {
                //agency {
                    //eq('id', profileRestService.currentUserProfile.stewardedOrganizations*.id)
                //}
            //}

            if (approvalStatus) {
                eq('approvalStatus', approvalStatus)
            }

            if (enabled != null) {
                eq('isEnabled', enabled)
            }
        }
    }

    @Override
    protected boolean canView(Listing si) {
        Profile profile = profileRestService.currentUserProfile

        //owners and admins can always view.  For others, there are more rules
        if (!profileRestService.isAdmin() && !si.isOwner(profile)) {

            //if it is enabled and approved it is visible to everyone
            if (!(si.isEnabled && si.approvalStatus == ApprovalStatus.APPROVED)) {
                return false
            }
        }

        return true
    }

    @Override
    protected void authorizeUpdate(Listing existing) {
        Profile profile = profileRestService.currentUserProfile

        //check enabled, approvalStatus, etc
        authorizeView(existing)

        def unauthorized = {
            throw new AccessDeniedException("Unauthorized attempt to modify ServiceItem with " +
                "id ${existing.id} by user ${profile.username}")
        }

        //admins can always edit
        if (!(profileRestService.isAdmin())) {
            //non-admin, non-owners can never edit
            if (!existing.isOwner(profile)) {
                unauthorized()
            }
        }
    }

    @Override
    protected void authorizeCreate(Listing newItem) {
        //anyone can create service items
    }

    @Override
    protected void postprocess(Listing updated, Map original = null) {

        if (original) {
            updateEnabledListingActivity(updated, original)
            updateApprovalStatus(updated, original)
            updated.save(failOnError: true)
            listingActivityInternalService.createChangeLog(updated, original)
        }
        else {
            updated.save(failOnError: true)
            listingActivityInternalService.addListingActivity(updated,
                Constants.Action.CREATED)
        }

        updateRelationshipsListingActivity(updated, original)
    }

    /**
     * Checks for changes to the approvalStatus flag and reacts accordingly.  This method
     * assumes that the ListingValidator has already bee run against this Listing
     */
    private void updateApprovalStatus(Listing updated, Map original) {
        def newApprovalStatus = updated.approvalStatus
        def oldApprovalStatus = original.approvalStatus

        if (newApprovalStatus != oldApprovalStatus) {
            switch (newApprovalStatus) {
                case ApprovalStatus.PENDING:
                    listingActivityInternalService.addListingActivity(updated,
                        Constants.Action.SUBMITTED)
                    break
                case ApprovalStatus.APPROVED:
                    doApprove(updated)
                    break
                default:
                    //should never happen assuming validateApprovalStatus has been run
                    throw new IllegalStateException("Unexpected approvalStatus transition in " +
                        "updateApprovalStatus: $oldApprovalStatus -> $newApprovalStatus")
            }
        }
    }

    /**
     * Business Logic that must happen when a listing is approved.  This method does not actually
     * do the approval, since that occurs during the update
     */
    private void doApprove(Listing si) {
        ListingActivity activity =
            listingActivityInternalService.addListingActivity(si,
                Constants.Action.APPROVED)

        profileRestService.checkAdmin()

        si.approvedDate = activity.activityDate
    }

    /**
     * Update the listing to be rejected.  This includes setting the approvalStatus, adding the
     * RejectionListing to the ServiceItem, and creating the RejectionActivity
     */
    public void reject(Listing si, RejectionListing rejectionListing) {
        if (si.approvalStatus != ApprovalStatus.PENDING) {
            throw new IllegalArgumentException("Cannot reject ServiceItem ${si.id} that has " +
                "approval status of ${si.approvalStatus}")
        }

        profileRestService.checkAdmin()

        si.approvalStatus = ApprovalStatus.REJECTED
        listingActivityInternalService.addRejectionActivity(si, rejectionListing)

        if (!si.rejectionListings?.contains(rejectionListing)) {
            si.addToRejectionListings(rejectionListing)
        }
    }

    @Override
    protected void populateDefaults(Listing dto) {
        Profile profile = profileRestService.currentUserProfile
        if(!dto.owners) {
            dto.addToOwners(profile)
        }
    }

    /**
     * Add a ListingActivity for the changing of the hidden flag (known externally as
     * enabled
     */
    private void updateEnabledListingActivity(Listing updated, Map old) {
        boolean oldIsEnabled = old.isEnabled, updatedIsEnabled = updated.isEnabled

        if (oldIsEnabled != updatedIsEnabled) {
            listingActivityInternalService.addListingActivity(updated,
                Constants.Action[updatedIsEnabled ? 'ENABLED' : 'DISABLED'])
        }
    }

    /**
     * Create the appropriate ListingActivities for any relationship changes
     */
    private void updateRelationshipsListingActivity(Listing updated, Map old) {
        Set<Listing> oldRelated = old?.required ?: new HashSet()
        Set<Listing> newRelated = updated.required

        Set<Listing> added = newRelated - oldRelated
        Set<Listing> removed = oldRelated - newRelated

        listingActivityInternalService.addRelationshipActivities(updated, added, removed)
    }

    /**
     * Create the necessary changelog entries, and unhook the necessary objects, to allow
     * this service item to be deleted
     */
    private void updateRelationshipsForDelete(Listing item) {
        //use a set to ensure no duplicates
        Set<Listing> relatedByListings = Listing.findAllByRequired(item) as Set
        Set<Listing> relatedToListings = item.required as Set

        relatedByListings.each { it.removeFromRequired(item) }
        relatedToListings.each { item.removeFromRequired(it) }

        //update ListingActivities
        relatedByListings.each {
            listingActivityInternalService.addRelationshipActivities(it, [], [item])
        }
        listingActivityInternalService.addRelationshipActivities(item, [],
            relatedToListings)

        //OP-5334: this save prevents the "collection not processed by flush" exception with Oracle and Postgresql
        item.save(flush: true)

        //unhook all ServiceItemSnapshots
        (ListingSnapshot.findAllByListing(item) as Set).each { it.listing = null }
    }
}
