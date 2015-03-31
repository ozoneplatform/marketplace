package marketplace.rest.resource

import java.util.concurrent.Future
import java.util.concurrent.Callable

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import marketplace.Listing

import marketplace.rest.service.ProfileRestService
import marketplace.rest.service.CategoryRestService
import marketplace.rest.service.TypeRestService
import marketplace.rest.service.IntentRestService
import marketplace.rest.service.ContactTypeRestService
import marketplace.rest.service.AgencyRestService
import marketplace.rest.service.ListingRestService

import marketplace.rest.StoreMetadata
import marketplace.rest.Storefront
import marketplace.rest.IwcApi

import marketplace.rest.representation.out.IwcApiRepresentation
import marketplace.rest.representation.out.StoreMetadataRepresentation
import marketplace.rest.representation.out.StorefrontRepresentation

@Path('api')
class RootResource {

    @Autowired ProfileRestService profileRestService
    @Autowired CategoryRestService categoryRestService
    @Autowired TypeRestService typeRestService
    @Autowired IntentRestService intentRestService
    @Autowired ContactTypeRestService contactTypeRestService
    @Autowired AgencyRestService agencyRestService
    @Autowired ListingRestService listingRestService

    @GET
    @Produces([
        IwcApiRepresentation.MEDIA_TYPE,
        MediaType.APPLICATION_JSON
    ])
    IwcApi readIwcApi() {
        new IwcApi(profileRestService.currentUserProfile)
    }

    @Path('metadata')
    @GET
    @Produces([
        StoreMetadataRepresentation.MEDIA_TYPE,
        MediaType.APPLICATION_JSON
    ])
    StoreMetadata getAllMetadata() {
        new StoreMetadata(
            categoryRestService.getAll(null, null),
            typeRestService.getAll(null, null),
            intentRestService.getAll(null, null),
            contactTypeRestService.getAll(null, null),
            agencyRestService.getAll(null, null)
        )
    }

    /**
     * A method to return the various listing information required for the
     * front page of the center-ui.  This includes featured listings, most
     * recent listings, and highest-rated listings
     */
    @Path('storefront')
    @GET
    @Produces([
        StorefrontRepresentation.MEDIA_TYPE,
        MediaType.APPLICATION_JSON
    ])
    Storefront getStorefrontInfo() {
        new Storefront(
            listingRestService.getFeatured(24),
            listingRestService.getRecent(24),
            listingRestService.getMostPopular(36)
        )
    }
}
