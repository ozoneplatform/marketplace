package marketplace.rest.resource.uribuilder

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.StoreMetadata

import marketplace.rest.resource.RootResource

class StoreMetadataUriBuilder implements CollectionUriBuilder<StoreMetadata> {
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private StoreMetadataUriBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.uriBuilderHolder = uriBuilderHolder
    }

    URI getCollectionUri() {
        uriBuilderHolder.builder
            .path(RootResource)
            .path(RootResource, 'getAllMetadata')
            .build()
    }

    @Component
    public static class Factory implements CollectionUriBuilder.Factory<StoreMetadata> {
        public CollectionUriBuilder<StoreMetadata> getBuilder(
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new StoreMetadataUriBuilder(uriBuilderHolder)
        }
    }
}
