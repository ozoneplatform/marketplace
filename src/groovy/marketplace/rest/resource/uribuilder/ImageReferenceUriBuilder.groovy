package marketplace.rest.resource.uribuilder

import javax.ws.rs.core.UriBuilder

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

import org.codehaus.groovy.grails.commons.GrailsApplication

import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.rest.resource.ImageResource
import marketplace.rest.service.ImageRestService
import marketplace.ImageReference

class ImageReferenceUriBuilder {
    private GrailsApplication grailsApplication
    private ImageRestService imageRestService
    private ApplicationRootUriBuilderHolder uriBuilderHolder

    ImageReferenceUriBuilder(
            GrailsApplication grailsApplication,
            ImageRestService imageRestService,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        this.grailsApplication = grailsApplication
        this.imageRestService = imageRestService
        this.uriBuilderHolder = uriBuilderHolder
    }

    URI getImageUri(ImageReference imageRef) {
        String imageUriBase = grailsApplication.config.marketplace.imageUriBaseOverride

        imageUriBase ? buildUriWithOverride(UriBuilder.fromUri(imageUriBase), imageRef)
          : buildUri(uriBuilderHolder.builder, imageRef)
    }

    //TODO This is a bit of a hack, since it requires reaching into the service layer and
    //ultimately the file system.  Perhaps figure out a better way some day
    URI getImageUri(UUID id) {
        getImageUri(imageRestService.getImageReference(id))
    }

    private URI buildUriWithOverride(UriBuilder base, ImageReference imageRef) {
        base.path(imageRestService.getRelativePath(imageRef).toString())
            .build()
    }

    private URI buildUri(UriBuilder base, ImageReference imageRef) {
        String extension = imageRestService.getFileExtension(imageRef)

        base.path(ImageResource)
            .path(ImageResource, 'getImage')
            .buildFromMap(id: imageRef.id, extension: extension)
    }

    @Component
    public static class Factory {
        @Autowired GrailsApplication grailsApplication
        @Autowired ImageRestService imageRestService

        ImageReferenceUriBuilder getBuilder(
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new ImageReferenceUriBuilder(grailsApplication, imageRestService, uriBuilderHolder)
        }
    }
}
