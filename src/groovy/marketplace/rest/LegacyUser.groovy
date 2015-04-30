package marketplace.rest

import marketplace.Profile

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
