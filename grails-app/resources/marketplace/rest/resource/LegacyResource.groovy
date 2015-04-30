package marketplace.rest.resource

import javax.ws.rs.PathParam
import javax.ws.rs.FormParam
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.Consumes
import javax.ws.rs.PUT
import javax.ws.rs.GET
import javax.ws.rs.DELETE
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import marketplace.rest.LegacyPreference
import marketplace.rest.LegacyUser
import marketplace.IwcDataObject
import marketplace.Profile
import marketplace.rest.service.ProfileRestService
import marketplace.rest.representation.out.LegacyPreferenceRepresentation

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
@Consumes(
    MediaType.APPLICATION_FORM_URLENCODED
)
class LegacyResource {

    @Autowired ProfileRestService profileRestService

    @Path('/preference/{namespace}/{name}')
    @PUT
    public LegacyPreference setPreference(
            @PathParam('namespace') String namespace,
            @PathParam('name') String name,
            @FormParam('value') String value) {

        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id
        String key = namespace + (char) 0x1E + name

        profileRestService.updateDataItem(userId, key, value, 'application/json')

        new LegacyPreference(namespace, name, value, currentUser)
    }

    @Path('/preference/{namespace}/{name}')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON
    ])
    public LegacyPreference getPreference(
        @PathParam('namespace') String namespace,
        @PathParam('name') String name) {

        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id
        String key = namespace + (char) 0x1E + name

        IwcDataObject data = profileRestService.getDataItem(userId, key)

        new LegacyPreference(namespace, name, data.entity, currentUser)

    }

    @Path('/preference/{namespace}/{name}')
    @DELETE
    @Produces([
        MediaType.APPLICATION_JSON
    ])
    public LegacyPreference deletePreference(
        @PathParam('namespace') String namespace,
        @PathParam('name') String name) {

        Profile currentUser = profileRestService.getCurrentUserProfile()
        Long userId = currentUser.id
        String key = namespace + (char) 0x1E + name

        IwcDataObject data = profileRestService.getDataItem(userId, key)
        profileRestService.deleteDataItem(userId, key)

        new LegacyPreference(namespace, name, data.entity, currentUser)
    }

    @Path('/person/whoami')
    @GET
    @Produces([
        MediaType.APPLICATION_JSON
    ])
    public LegacyUser getCurrentUser() {
        Profile currentUser = profileRestService.getCurrentUserProfile()

        new LegacyUser(currentUser)
    }

}
