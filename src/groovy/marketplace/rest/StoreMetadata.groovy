package marketplace.rest

import marketplace.Category
import marketplace.Type
import marketplace.Intent
import marketplace.ContactType
import marketplace.Agency

class StoreMetadata {
    final Collection<Category> categories
    final Collection<Type> types
    final Collection<Intent> intents
    final Collection<ContactType> contactTypes
    final Collection<Agency> organizations

    public StoreMetadata(
            Collection<Category> categories,
            Collection<Type> types,
            Collection<Intent> intents,
            Collection<ContactType> contactTypes,
            Collection<Agency> organizations) {
        this.categories = Collections.unmodifiableCollection(categories)
        this.types = Collections.unmodifiableCollection(types)
        this.intents = Collections.unmodifiableCollection(intents)
        this.contactTypes = Collections.unmodifiableCollection(contactTypes)
        this.organizations = Collections.unmodifiableCollection(organizations)
   }
}
