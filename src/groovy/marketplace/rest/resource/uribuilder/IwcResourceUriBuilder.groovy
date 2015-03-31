package marketplace.rest.resource.uribuilder

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.resource.RootResource

import marketplace.rest.IwcApi

//TODO is this class redundant with IwcUriBuilder
class RootResourceUriBuilder implements CollectionUriBuilder<IwcApi> {
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private RootResourceUriBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.uriBuilderHolder = uriBuilderHolder
    }

    URI getCollectionUri() {
        uriBuilderHolder.builder
            .path(RootResource.class)
            .build()
    }

    @Component
    public static class Factory implements CollectionUriBuilder.Factory {
        RootResourceUriBuilder getBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new RootResourceUriBuilder(uriBuilderHolder)
        }
    }
}
