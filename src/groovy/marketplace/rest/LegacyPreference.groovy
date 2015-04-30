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

class LegacyUser {
	String currentUserName, currentUser
	Date currentUserPrevLogin
	Number currentId

	LegacyUser(Profile user) {
		this.currentUserName = user.username
		this.currentUser = user.displayName
		this.currentUserPrevLogin = user.lastLogin
		this.currentId = user.id
	}
}
