package marketplace.rest

/**
 * TODO This class is currently mocked out to have a minimal impl for compilation
 * while other components are written.  It will need to be rewritten
 */
class LegacyPreference {
    String namespace, name, value

    LegacyPreference(String namespace, String name, String value) {
    	this.namespace = namespace
    	this.name = name
    	this.value = value
    }
}
