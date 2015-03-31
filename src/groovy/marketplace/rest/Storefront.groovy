package marketplace.rest

import marketplace.Listing

/**
 * This class represents a grouping of all of the listing data needed by the initial page
 * of the UI
 */
class Storefront {
    final List<Listing> featured
    final List<Listing> recent
    final List<Listing> mostPopular

    public Storefront(
            List<Listing> featured,
            List<Listing> recent,
            List<Listing> mostPopular) {
        this.featured = featured
        this.recent = recent
        this.mostPopular = mostPopular
    }
}
