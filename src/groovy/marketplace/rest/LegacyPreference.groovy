package marketplace.rest

import marketplace.Profile

class LegacyPreference {
    String namespace, name, value
    Profile user

    LegacyPreference(String namespace, String name, String value, Profile user) {
    	this.namespace = namespace
    	this.name = name
    	this.value = value
    	this.user = user
    }
}
