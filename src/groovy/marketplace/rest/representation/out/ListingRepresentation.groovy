package marketplace.rest.representation.out

import marketplace.Contact
import marketplace.ContactType
import marketplace.DocUrl
import marketplace.Listing
import marketplace.Profile
import marketplace.Screenshot
import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.HalLinks
import marketplace.hal.Link
import marketplace.hal.OzpRelationType
import marketplace.hal.RepresentationFactory
import marketplace.hal.SelfRefRepresentation
import marketplace.rest.resource.uribuilder.ListingUriBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

class ListingRepresentation extends SelfRefRepresentation<Listing> {
    public static final String MEDIA_TYPE = 'application/vnd.ozp-listing-v1+json'
    public static final String COLLECTION_MEDIA_TYPE = 'application/vnd.ozp-listings-v1+json'

    private Listing listing

    public ListingRepresentation(Listing listing, ApplicationRootUriBuilderHolder uriBuilderHolder,
                                 ListingUriBuilder listingUriBuilder) {
        super(listingUriBuilder.getUri(listing), createLinks(listing, listingUriBuilder), null)

        this.listing = listing
    }

    Long getId() { listing.id }
    Boolean getIsEnabled() { listing.isEnabled }
    Boolean getIsFeatured() { listing.isFeatured }
    String getUuid() { listing.uuid }
    String getTitle() { listing.title }
    String getDescription() { listing.description }
    String getDescriptionShort() { listing.descriptionShort }
    Long getTotalComments() { listing.totalComments }
    String getImageSmallUrl() { listing.imageSmallUrl }
    String getImageMediumUrl() { listing.imageMediumUrl }
    String getImageLargeUrl() { listing.imageLargeUrl }
    String getImageXlargeUrl() { listing.imageXlargeUrl }
    String getVersionName() { listing.versionName }
    String getRequirements() { listing.requirements }
    String getWhatIsNew() { listing.whatIsNew }
    Date getApprovedDate() { listing.approvedDate }
    String getApprovalStatus() { listing.approvalStatus.toString() }
    Collection<String> getTags() { listing.tags }
    String getType() { listing.type.title }
    String getAgency() { listing.agency.title }

    Collection<String> getCategories() { listing.categories.collect { it.title }}

    Collection<String> getIntents() { listing.intents.collect {
        "${it.type}/${it.action}".toString()
    }}

    Collection<ScreenshotRepresentation> getScreenShots() { listing.screenshots.collect {
        new ScreenshotRepresentation(it)
    }}

    Collection<ContactRepresentation> getContacts() { listing.contacts.collect {
        new ContactRepresentation(it)
    }}

    Collection<DocUrlRepresentation> getDocUrls() { listing.docUrls.collect {
        new DocUrlRepresentation(it)
    }}

    Collection<OwnerRepresentation> getOwners() { listing.owners.collect {
        new OwnerRepresentation(it)
    }}

    private static HalLinks createLinks(Listing listing, ListingUriBuilder listingUriBuilder) {
        URI activitiesUri = listingUriBuilder.getListingActivitiesUri(listing),
            commentsUri = listingUriBuilder.getListingCommentsUri(listing),
            requiredUri = listingUriBuilder.getRequiredListingsUri(listing),
            requiringUri = listingUriBuilder.getRequiringListingsUri(listing)

        new HalLinks([
            new AbstractMap.SimpleEntry(OzpRelationType.ACTIVITY, new Link(activitiesUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.REQUIRED, new Link(requiredUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.REQUIRED_BY, new Link(requiringUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.REVIEW, new Link(commentsUri))
        ])
    }

    @Component
    public static class Factory implements RepresentationFactory<Listing> {
        @Autowired
        ListingUriBuilder.Factory listingUriBuilderFactory

        @Override
        public ListingRepresentation toRepresentation(Listing listing,
                                                      ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new ListingRepresentation(listing, uriBuilderHolder,
                    listingUriBuilderFactory.getBuilder(uriBuilderHolder))
        }
    }
}

class ContactRepresentation {
    private Contact contact

    ContactRepresentation(Contact contact) {
        this.contact = contact
    }

    String getName() { contact.name }
    String getOrganization() { contact.organization }
    String getEmail() { contact.email }
    String getUnsecurePhone() { contact.unsecurePhone }
    String getSecurePhone() { contact.securePhone }
    String getType() { contact.type.title }
}

class DocUrlRepresentation {
    private DocUrl docUrl

    DocUrlRepresentation(DocUrl docUrl) {
        this.docUrl = docUrl
    }

    String getName() { docUrl.name }
    String getUrl() { docUrl.url }
}

class OwnerRepresentation {
    private Profile owner

    OwnerRepresentation(Profile owner) {
        this.owner = owner
    }

    String getUsername() { owner.username }
    String getDisplayName() { owner.displayName }
}

class ScreenshotRepresentation {
    private Screenshot screenshot

    ScreenshotRepresentation(Screenshot screenshot) {
        this.screenshot = screenshot
    }

    String getSmallImageUrl() { screenshot.smallImageUrl }
    String getLargeImageUrl() { screenshot.largeImageUrl }
}