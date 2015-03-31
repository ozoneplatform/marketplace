package marketplace.rest.representation.out

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import marketplace.Listing

import marketplace.hal.SelfRefRepresentation
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory
import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.Storefront

import marketplace.rest.resource.uribuilder.CollectionUriBuilder
import marketplace.rest.resource.uribuilder.StorefrontUriBuilder

class StorefrontRepresentation extends SelfRefRepresentation<Storefront> {
    public static final String MEDIA_TYPE = 'application/vnd.ozp-storefront-v1+json'

    private List<AbstractHalRepresentation<Listing>> featuredReps
    private List<AbstractHalRepresentation<Listing>> recentReps
    private List<AbstractHalRepresentation<Listing>> mostPopularReps

    private StorefrontRepresentation(
            Storefront storefront,
            CollectionUriBuilder<Storefront> storefrontUriBuilder,
            RepresentationFactory<Listing> listingRepFactory,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        super(storefrontUriBuilder.collectionUri, null, null)

        def toRep = { listing -> listingRepFactory.toRepresentation(listing, uriBuilderHolder) }

        featuredReps = storefront.featured.collect(toRep)
        recentReps = storefront.recent.collect(toRep)
        mostPopularReps = storefront.mostPopular.collect(toRep)
    }

    public List<AbstractHalRepresentation<Listing>> getFeatured() { featuredReps }
    public List<AbstractHalRepresentation<Listing>> getRecent() { recentReps }
    public List<AbstractHalRepresentation<Listing>> getMostPopular() { mostPopularReps }

    @Component
    public static class Factory implements RepresentationFactory<Storefront> {
        @Autowired StorefrontUriBuilder.Factory storefrontUriBuilderFactory
        @Autowired ListingRepresentation.Factory listingRepFactory

        AbstractHalRepresentation<Storefront> toRepresentation(
                Storefront storefront,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new StorefrontRepresentation(
                storefront,
                storefrontUriBuilderFactory.getBuilder(uriBuilderHolder),
                listingRepFactory,
                uriBuilderHolder
            )
        }
    }
}
