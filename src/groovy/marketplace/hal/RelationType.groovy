package marketplace.hal

//all RelationType subclasses must be comparable to each other, based on their name
interface RelationType extends Comparable<RelationType> {
    String getRelationName()
}

/**
 * A standard Link relation defined by the IANA.  These do not need namespacing.
 */
enum RegisteredRelationType implements RelationType {
    ABOUT('about'),
    ALTERNATE('alternate'),
    APPENDIX('appendix'),
    ARCHIVES('archives'),
    AUTHOR('author'),
    BOOKMARK('bookmark'),
    CANONICAL('canonical'),
    CHAPTER('chapter'),
    COLLECTION('collection'),
    CONTENTS('contents'),
    COPYRIGHT('copyright'),
    CREATE_FORM('create-form'),
    CURRENT('current'),
    DESCRIBEDBY('describedby'),
    DESCRIBES('describes'),
    DISCLOSURE('disclosure'),
    DUPLICATE('duplicate'),
    EDIT('edit'),
    EDIT_FORM('edit-form'),
    EDIT_MEDIA('edit-media'),
    ENCLOSURE('enclosure'),
    FIRST('first'),
    GLOSSARY('glossary'),
    HELP('help'),
    HOSTS('hosts'),
    HUB('hub'),
    ICON('icon'),
    INDEX('index'),
    ITEM('item'),
    LAST('last'),
    LATEST_VERSION('latest-version'),
    LICENSE('license'),
    LRDD('lrdd'),
    MEMENTO('memento'),
    MONITOR('monitor'),
    MONITOR_GROUP('monitor-group'),
    NEXT('next'),
    NEXT_ARCHIVE('next-archive'),
    NOFOLLOW('nofollow'),
    NOREFERRER('noreferrer'),
    ORIGINAL('original'),
    PAYMENT('payment'),
    PREDECESSOR_VERSION('predecessor-version'),
    PREFETCH('prefetch'),
    PREV('prev'),
    PREVIEW('preview'),
    PREVIOUS('previous'),
    PREV_ARCHIVE('prev-archive'),
    PRIVACY_POLICY('privacy-policy'),
    PROFILE('profile'),
    RELATED('related'),
    REPLIES('replies'),
    SEARCH('search'),
    SECTION('section'),
    SELF('self'),
    SERVICE('service'),
    START('start'),
    STYLESHEET('stylesheet'),
    SUBSECTION('subsection'),
    SUCCESSOR_VERSION('successor-version'),
    TAG('tag'),
    TERMS_OF_SERVICE('terms-of-service'),
    TIMEGATE('timegate'),
    TIMEMAP('timemap'),
    TYPE('type'),
    UP('up'),
    VERSION_HISTORY('version-history'),
    VIA('via'),
    WORKING_COPY('working-copy'),
    WORKING_COPY_OF('working-copy-of')

    final String relationName

    public String toString() { relationName }

    private RegisteredRelationType(String relationName) {
        this.relationName = relationName
    }

    int compareTo(RelationType other) {
        this.relationName <=> other.relationName
    }
}

/**
 * The special "curie" relation type specified for use in HAL
 */
enum CurieRelationType implements RelationType {
    CURIE

    String getRelationName() { 'curies' }
    public String toString() { relationName }

    int compareTo(RelationType other) {
        this.relationName <=> other.relationName
    }
}

/**
 * A non-standard, non-curied relation, which must be represented by a URI
 */
class ExtensionRelationType implements RelationType {
    private URI relationName

    ExtensionRelationType(String relationName) {
        ExtensionRelation(new URI(relationName))
    }

    ExtensionRelationType(URI relationName) {
        if (!relationName) {
            throw new NullPointerException()
        }

        this.relationName = relationName
    }

    String getRelationName() { relationName.toString() }
    public String toString() { relationName }

    int compareTo(RelationType other) {
        this.getRelationName() <=> other.getRelationName()
    }

    boolean equals(Object other) {
        other instanceof RelationType && !(this <=> other)
    }

    int hashCode() {
        getRelationName().hashCode()
    }
}

/**
 * An extension relation that can be represented in a HAL document using the CURIE system
 */
interface HalCuriedRelationType extends RelationType {
    /**
     * @return the CURIE'd URI (e.g. namespace)
     */
    HalRelationCurie getHalRelationCurie()

    /**
     * @return the curied name of the relation type.  This name is not valid unless the curie
     * link is present alongside it
     */
    String getRelationName()
}

enum OzpRelationType implements HalCuriedRelationType {
    //defined relations for OZP should be enumerated here
    APPLICATION('application'),
    INTENT('intent'),
    USER_DATA('user-data'),
    USER('user'),
    APPLICATION_LIBRARY('application-library'),
    ORGANIZATION('organization'),
    SYSTEM('system'),
    REVIEW('review'),
    ACTIVITY('activity'),
    REQUIRED('required'),
    REQUIRED_BY('required-by'),
    CATEGORY('category'),
    TYPE('type'),
    CONTACT_TYPE('contact-type')

    private String name

    private OzpRelationType(String name) {
        this.name = name
    }

    HalRelationCurie getHalRelationCurie() { HalRelationCurie.OZP }

    //TODO determine if this can be put somewhere else so it doesn't need to be re-implemented
    //in each HalCuriedRelationType
    String getRelationName() { "${halRelationCurie.name}:${name}" }
    public String toString() { relationName }

    int compareTo(RelationType other) {
        this.relationName <=> other.relationName
    }
}
