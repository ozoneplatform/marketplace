package marketplace.rest.representation.out

import javax.ws.rs.core.UriBuilder
import javax.ws.rs.core.UriInfo

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin

import marketplace.ApplicationLibraryEntry
import marketplace.Profile
import marketplace.Listing

import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.RepresentationFactory
import marketplace.hal.RegisteredRelationType
import marketplace.hal.OzpRelationType

import marketplace.rest.ApplicationLibrary

@TestMixin(GrailsUnitTestMixin)
class ApplicationLibraryRepresentationUnitTest {
    RepresentationFactory<ApplicationLibrary> factory =
        new ApplicationLibraryRepresentation.Factory()

    ApplicationRootUriBuilderHolder uriBuilderHolder = new ApplicationRootUriBuilderHolder([
        getBaseUriBuilder: {
            UriBuilder.fromPath('https://localhost/asdf/')
        }
    ] as UriInfo)

    void testLinks() {
        long profileId = 132
        ApplicationLibrary library = new ApplicationLibrary(profileId, [])
        ApplicationLibraryRepresentation rep = factory.toRepresentation(library, uriBuilderHolder)

        assert rep.links.toMap().get(RegisteredRelationType.SELF).href ==
            'https://localhost/asdf/api/profile/132/library'
        assert rep.links.toMap().get(RegisteredRelationType.VIA).href ==
            'https://localhost/asdf/api/profile/132'
    }

    //test the links on embedded objects
    void testEmbedded() {
        long profileId = 132
        Collection<Listing> serviceItems = [1,2,3].collect {
            Listing si = new Listing(
                title: "Listing $it",
                launchUrl: "https://localhost/$it"
            )
            si.id = it
            return si
        }

        Collection<Profile> profiles = [4,5,6].collect {
            Profile profile = new Profile()
            profile.id = it
            return profile
        }

        Collection<ApplicationLibraryEntry> entries = [
            new ApplicationLibraryEntry(
                folder: 'folder 1',
                listing: serviceItems[0],
                owner: profiles[0]
            ),
            new ApplicationLibraryEntry(
                folder: null,
                listing: serviceItems[1],
                owner: profiles[1]
            ),
            new ApplicationLibraryEntry(
                folder: 'folder 1',
                listing: serviceItems[2],
                owner: profiles[2]
            )
        ]

        ApplicationLibrary library = new ApplicationLibrary(profileId, entries)
        ApplicationLibraryRepresentation rep = factory.toRepresentation(library, uriBuilderHolder)

        Collection folders = rep.embedded.toMap().get(RegisteredRelationType.ITEM)

        assert folders.size() == 2
        AbstractHalRepresentation folder = folders.find { it.folder == 'folder 1' }
        AbstractHalRepresentation rootFolder = folders.find { it.folder == null }

        assert folder.embedded.toMap().get(OzpRelationType.APPLICATION).collect { it.title } ==
            ["Listing 1", "Listing 3"]

        assert folder.embedded.toMap().get(OzpRelationType.APPLICATION).collect {
            it.links.toMap().get(RegisteredRelationType.SELF).href
        } == [
            "https://localhost/asdf/api/listing/1",
            "https://localhost/asdf/api/listing/3"
        ]

        assert folder.embedded.toMap().get(OzpRelationType.APPLICATION).collect {
            it.links.toMap().get(RegisteredRelationType.DESCRIBES).href
        } == [
            "https://localhost/1",
            "https://localhost/3"
        ]

        assert rootFolder.embedded.toMap().get(OzpRelationType.APPLICATION).title == "Listing 2"

        assert rootFolder.embedded.toMap().get(OzpRelationType.APPLICATION)
            .links.toMap().get(RegisteredRelationType.SELF).href ==
                "https://localhost/asdf/api/listing/2"


        assert rootFolder.embedded.toMap().get(OzpRelationType.APPLICATION)
            .links.toMap().get(RegisteredRelationType.DESCRIBES).href ==
                "https://localhost/2"
    }
}
