package marketplace.rest.representation.out

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import marketplace.hal.SelfRefRepresentation
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory
import marketplace.hal.HalLinks
import marketplace.hal.Link
import marketplace.hal.HalEmbedded
import marketplace.hal.OzpRelationType
import marketplace.hal.ApplicationRootUriBuilderHolder

import marketplace.Category
import marketplace.Type
import marketplace.Intent
import marketplace.ContactType
import marketplace.Agency

import marketplace.rest.StoreMetadata

import marketplace.rest.resource.uribuilder.CollectionUriBuilder
import marketplace.rest.resource.uribuilder.CategoryUriBuilder
import marketplace.rest.resource.uribuilder.TypeUriBuilder
import marketplace.rest.resource.uribuilder.IntentUriBuilder
import marketplace.rest.resource.uribuilder.ContactTypeUriBuilder
import marketplace.rest.resource.uribuilder.AgencyUriBuilder
import marketplace.rest.resource.uribuilder.StoreMetadataUriBuilder

class StoreMetadataRepresentation extends SelfRefRepresentation<StoreMetadata> {
    public static final String MEDIA_TYPE = 'application/vnd.ozp-metadata-v1+json'

    private StoreMetadataRepresentation(
            StoreMetadata storeMetadata,
            CollectionUriBuilder<StoreMetadata> storeMetadataUriBuilder,
            CollectionUriBuilder<Category> categoryUriBuilder,
            CollectionUriBuilder<Type> typeUriBuilder,
            CollectionUriBuilder<Intent> intentUriBuilder,
            CollectionUriBuilder<ContactType> contactTypeUriBuilder,
            CollectionUriBuilder<Agency> agencyUriBuilder,
            RepresentationFactory<Collection<Category>> categoryRepFactory,
            RepresentationFactory<Collection<Type>> typeRepFactory,
            RepresentationFactory<Collection<Intent>> intentRepFactory,
            RepresentationFactory<Collection<ContactType>> contactTypeRepFactory,
            RepresentationFactory<Collection<Agency>> agencyRepFactory,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        super(storeMetadataUriBuilder.collectionUri,
            createLinks(categoryUriBuilder, typeUriBuilder, intentUriBuilder,
                contactTypeUriBuilder, agencyUriBuilder),
            createEmbedded(storeMetadata, categoryRepFactory, typeRepFactory,
                intentRepFactory, contactTypeRepFactory, agencyRepFactory, uriBuilderHolder)
        )
    }

    private static HalLinks createLinks(
            CollectionUriBuilder<Category> categoryUriBuilder,
            CollectionUriBuilder<Type> typeUriBuilder,
            CollectionUriBuilder<Intent> intentUriBuilder,
            CollectionUriBuilder<ContactType> contactTypeUriBuilder,
            CollectionUriBuilder<Agency> agencyUriBuilder) {
        new HalLinks([
            new AbstractMap.SimpleEntry(OzpRelationType.CATEGORY,
                new Link(categoryUriBuilder.collectionUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.TYPE,
                new Link(typeUriBuilder.collectionUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.INTENT,
                new Link(intentUriBuilder.collectionUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.CONTACT_TYPE,
                new Link(contactTypeUriBuilder.collectionUri)),
            new AbstractMap.SimpleEntry(OzpRelationType.ORGANIZATION,
                new Link(agencyUriBuilder.collectionUri))
        ])
    }

    private static HalEmbedded createEmbedded(
            StoreMetadata storeMetadata,
            RepresentationFactory<Collection<Category>> categoryRepFactory,
            RepresentationFactory<Collection<Type>> typeRepFactory,
            RepresentationFactory<Collection<Intent>> intentRepFactory,
            RepresentationFactory<Collection<ContactType>> contactTypeRepFactory,
            RepresentationFactory<Collection<Agency>> agencyRepFactory,
            ApplicationRootUriBuilderHolder uriBuilderHolder) {
        new HalEmbedded([
            new AbstractMap.SimpleEntry(OzpRelationType.CATEGORY,
                categoryRepFactory.toRepresentation(storeMetadata.categories, uriBuilderHolder)),
            new AbstractMap.SimpleEntry(OzpRelationType.TYPE,
                typeRepFactory.toRepresentation(storeMetadata.types, uriBuilderHolder)),
            new AbstractMap.SimpleEntry(OzpRelationType.INTENT,
                intentRepFactory.toRepresentation(storeMetadata.intents, uriBuilderHolder)),
            new AbstractMap.SimpleEntry(OzpRelationType.CONTACT_TYPE,
                contactTypeRepFactory.toRepresentation(storeMetadata.contactTypes,
                    uriBuilderHolder)),
            new AbstractMap.SimpleEntry(OzpRelationType.ORGANIZATION,
                agencyRepFactory.toRepresentation(storeMetadata.organizations, uriBuilderHolder)),
        ])
    }

    @Component
    public static class Factory implements RepresentationFactory<StoreMetadata> {
        @Autowired CategoryUriBuilder.Factory categoryUriBuilderFactory
        @Autowired TypeUriBuilder.Factory typeUriBuilderFactory
        @Autowired IntentUriBuilder.Factory intentUriBuilderFactory
        @Autowired ContactTypeUriBuilder.Factory contactTypeUriBuilderFactory
        @Autowired AgencyUriBuilder.Factory agencyUriBuilderFactory
        @Autowired StoreMetadataUriBuilder.Factory storeMetadataUriBuilderFactory

        @Autowired CategoryRepresentation.Factory categoryRepFactory
        @Autowired TypeRepresentation.Factory typeRepFactory
        @Autowired IntentRepresentation.Factory intentRepFactory
        @Autowired ContactTypeRepresentation.Factory contactTypeRepFactory
        @Autowired AgencyRepresentation.Factory agencyRepFactory

        private RepresentationFactory<Category> categoriesRepFactory
        private RepresentationFactory<Type> typesRepFactory
        private RepresentationFactory<Intent> intentsRepFactory
        private RepresentationFactory<ContactType> contactTypesRepFactory
        private RepresentationFactory<Agency> agenciesRepFactory

        @PostConstruct
        private void createCollectionFactories() {
            categoriesRepFactory =
                EmbeddedCollectionRepresentation.createFactory(categoryRepFactory,
                    categoryUriBuilderFactory)

            typesRepFactory =
                EmbeddedCollectionRepresentation.createFactory(typeRepFactory,
                    typeUriBuilderFactory)

            intentsRepFactory =
                EmbeddedCollectionRepresentation.createFactory(intentRepFactory,
                    intentUriBuilderFactory)

            contactTypesRepFactory =
                EmbeddedCollectionRepresentation.createFactory(contactTypeRepFactory,
                    contactTypeUriBuilderFactory)

            agenciesRepFactory =
                EmbeddedCollectionRepresentation.createFactory(agencyRepFactory,
                    agencyUriBuilderFactory)

        }

        public AbstractHalRepresentation<StoreMetadata> toRepresentation(
                StoreMetadata storeMetadata,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new StoreMetadataRepresentation(
                storeMetadata,
                storeMetadataUriBuilderFactory.getBuilder(uriBuilderHolder),
                categoryUriBuilderFactory.getBuilder(uriBuilderHolder),
                typeUriBuilderFactory.getBuilder(uriBuilderHolder),
                intentUriBuilderFactory.getBuilder(uriBuilderHolder),
                contactTypeUriBuilderFactory.getBuilder(uriBuilderHolder),
                agencyUriBuilderFactory.getBuilder(uriBuilderHolder),
                categoriesRepFactory,
                typesRepFactory,
                intentsRepFactory,
                contactTypesRepFactory,
                agenciesRepFactory,
                uriBuilderHolder
            )
        }
    }
}
