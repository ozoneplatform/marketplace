package marketplace.rest.writer

import org.codehaus.groovy.grails.commons.GrailsApplication

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.LegacyUser

import marketplace.hal.AbstractRepresentationWriter

import marketplace.rest.representation.out.LegacyUserRepresentation

@Provider
@Produces(MediaType.APPLICATION_JSON)
class LegacyUserRepresentationWriter extends
        AbstractRepresentationWriter<LegacyUser> {

    @Autowired
    LegacyUserRepresentationWriter(GrailsApplication grailsApplication,
            LegacyUserRepresentation.Factory factory) {
        super(grailsApplication, factory)
    }
}
