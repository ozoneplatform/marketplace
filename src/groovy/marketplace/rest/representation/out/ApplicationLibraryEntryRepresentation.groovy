package marketplace.rest.representation.out

import marketplace.ApplicationLibraryEntry
import marketplace.ServiceItem

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.RegisteredRelationType
import marketplace.hal.OzpRelationType
import marketplace.hal.HalLinks
import marketplace.hal.Link
import marketplace.hal.HalEmbedded
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.rest.resource.ProfileResource

class ApplicationLibraryEntryRepresentation
        extends AbstractHalRepresentation<ApplicationLibraryEntry> {
    public static final String MEDIA_TYPE = 'application/vnd.ozp-library-entry-v1+json'

    private ApplicationLibraryEntry entry

    private ApplicationLibraryEntryRepresentation(ApplicationLibraryEntry entry,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        super(createLinks(entry, uriBuilderHolder),
            createEmbeddedServiceItem(entry, uriBuilderHolder))

        assert entry != null
        assert entry.serviceItem != null

        this.entry = entry
    }

    private static HalLinks createLinks(ApplicationLibraryEntry entry,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        URI collectionHref = uriBuilderHolder.builder
                .path(ProfileResource.class)
                .path(ProfileResource.class, 'getApplicationLibrary')
                .buildFromMap(profileId: entry.owner.id),
            entryHref = uriBuilderHolder.builder
                .path(ProfileResource.class)
                .path(ProfileResource.class, 'removeFromApplicationLibrary')
                .buildFromMap(profileId: entry.owner.id, serviceItemId: entry.serviceItem.id)

        new HalLinks([
            new AbstractMap.SimpleEntry(RegisteredRelationType.COLLECTION,
                new Link(collectionHref)),
            new AbstractMap.SimpleEntry(RegisteredRelationType.SELF, new Link(entryHref))
        ])
    }

    private static HalEmbedded createEmbeddedServiceItem(ApplicationLibraryEntry entry,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        new HalEmbedded(OzpRelationType.APPLICATION,
            new LibraryApplicationRepresentation(entry, uriBuilderHolder))
    }

    public String getFolder() { entry.folder }
    public IdRefRepresentation<ServiceItem> getServiceItem() {
        new IdRefRepresentation<ServiceItem>(entry.serviceItem)
    }

    public static class Factory
            implements RepresentationFactory<ApplicationLibraryEntry> {

        @Override
        ApplicationLibraryEntryRepresentation toRepresentation(ApplicationLibraryEntry entry,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new ApplicationLibraryEntryRepresentation(entry, uriBuilderHolder)
        }
    }
}