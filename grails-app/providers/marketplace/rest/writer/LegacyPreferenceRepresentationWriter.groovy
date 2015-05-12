package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.LegacyPreference

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.LegacyPreferenceRepresentation

@Provider
@Produces(MediaType.APPLICATION_JSON)
class LegacyPreferenceRepresentationWriter extends
        AbstractRepresentationWriter<LegacyPreference> {

    @Autowired
    LegacyPreferenceRepresentationWriter(GrailsApplication grailsApplication,
            LegacyPreferenceRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
