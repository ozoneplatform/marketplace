package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.StoreMetadata

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.StoreMetadataRepresentation

@Provider
@Produces([
    StoreMetadataRepresentation.MEDIA_TYPE,
    MediaType.APPLICATION_JSON
])
class StoreMetadataRepresentationWriter extends AbstractRepresentationWriter<StoreMetadata> {

    @Autowired
    StoreMetadataRepresentationWriter(GrailsApplication grailsApplication,
            StoreMetadataRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
