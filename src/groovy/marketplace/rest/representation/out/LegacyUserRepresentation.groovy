package marketplace.rest.representation.out

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.rest.LegacyUser

class LegacyUserRepresentation extends AbstractHalRepresentation<LegacyUser> {
    private LegacyUser legacyUser

    private LegacyUserRepresentation(LegacyUser legacyUser) {
        this.legacyUser = legacyUser
    }

    public String getCurrentUserName() { legacyUser.currentUserName }
    public String getCurrentUser() { legacyUser.currentUser }
    public Date getCurrentUserPrevLogin() { legacyUser.currentUserPrevLogin }
    public Number getCurrentId() { legacyUser.currentId }

    @Component
    public static class Factory implements RepresentationFactory<LegacyUser> {
        AbstractHalRepresentation<LegacyUser> toRepresentation(
                LegacyUser legacyUser,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new LegacyUserRepresentation(legacyUser)
        }
    }
}
