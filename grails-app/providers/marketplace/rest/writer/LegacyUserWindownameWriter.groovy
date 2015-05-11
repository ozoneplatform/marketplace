package marketplace.rest.writer

import org.springframework.beans.factory.annotation.Autowired

import javax.ws.rs.ext.Provider
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import marketplace.rest.LegacyUser

import marketplace.hal.AbstractWindownameWriter

@Provider
@Produces([MediaType.TEXT_HTML])
class LegacyUserWindownameWriter extends
        AbstractWindownameWriter<LegacyUser> {
    @Autowired
    LegacyUserWindownameWriter(LegacyUserRepresentationWriter writer) {
        super(writer)
    }
}
