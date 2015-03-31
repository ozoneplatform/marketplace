package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.Storefront

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.StorefrontRepresentation

@Provider
@Produces([
    StorefrontRepresentation.MEDIA_TYPE,
    MediaType.APPLICATION_JSON
])
class StorefrontRepresentationWriter extends AbstractRepresentationWriter<Storefront> {

    @Autowired
    StorefrontRepresentationWriter(GrailsApplication grailsApplication,
            StorefrontRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
