package marketplace.rest.resource.uribuilder

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.Storefront

import marketplace.rest.resource.RootResource

class StorefrontUriBuilder implements CollectionUriBuilder<Storefront> {
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private StorefrontUriBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.uriBuilderHolder = uriBuilderHolder
    }

    URI getCollectionUri() {
        uriBuilderHolder.builder
            .path(RootResource)
            .path(RootResource, 'getStorefrontInfo')
            .build()
    }

    @Component
    public static class Factory implements CollectionUriBuilder.Factory<Storefront> {
        public CollectionUriBuilder<Storefront> getBuilder(
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new StorefrontUriBuilder(uriBuilderHolder)
        }
    }
}

