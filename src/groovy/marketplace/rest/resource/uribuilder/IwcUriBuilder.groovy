package marketplace.rest.resource.uribuilder

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.resource.RootResource
import marketplace.rest.resource.IwcSystemResource

import marketplace.rest.IwcApi

class IwcUriBuilder implements CollectionUriBuilder<IwcApi> {
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    private IwcUriBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.uriBuilderHolder = uriBuilderHolder
    }

    URI getCollectionUri() {
        uriBuilderHolder.builder
            .path(RootResource.class)
            .build()
    }

    URI getSystemUri() {
        uriBuilderHolder.builder
            .path(IwcSystemResource.class)
            .build()
    }

    @Component
    public static class Factory implements CollectionUriBuilder.Factory {
        IwcUriBuilder getBuilder(ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new IwcUriBuilder(uriBuilderHolder)
        }
    }
}
