package marketplace.rest.resource

import javax.ws.rs.PathParam
import javax.ws.rs.FormParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.MediaType

import marketplace.rest.LegacyPreference
import marketplace.Profile
import marketplace.rest.service.ProfileRestService

import org.springframework.beans.factory.annotation.Autowired

/**
 * This class contains endpoints that are a carry-over (interface-wise) from OWF 7.x
 * and earlier.  These endpoints still need to exist to support OWF 7 widget compatibility.
 */
@Path('/api/prefs')
@Produces([
    MediaType.APPLICATION_JSON,
    MediaType.TEXT_HTML
])
class LegacyResource {

    @Autowired ProfileRestService profileRestService

    @Path('/preference/{namespace}/{name}')
    @PUT
    public LegacyPreference setPreference(
            @PathParam('namespace') String namespace,
            @PathParam('name') String name,
            @FormParam('value') String value) {
        //TODO real impl
        Profile currentUser = profileRestService.getCurrentUser()
        Long userId = currentUser.get(id)
        String key = namespace + 0x1E + name;

        // updateDataItem(Long userId, String key, String entity, String contentType)
        profileRestService.updateDataItem(userId, key, value, 'application/json')

        new LegacyPreference(namespace: namespace, name: name, value: value)
    }
}
