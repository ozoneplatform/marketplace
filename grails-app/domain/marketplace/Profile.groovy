package marketplace

import org.grails.web.json.JSONObject

import marketplace.JSONUtil as JS
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder

import ozone.utils.Utils


class Profile extends AuditStamped implements Serializable, ToJSON {

    static bindableProperties = ['bio', 'username', 'displayName', 'email', 'uuid']
    static modifiableReferenceProperties = []

    static searchable = {
        root false
        avatar component: true
        username index: 'not_analyzed', excludeFromAll: false
        displayName index: 'analyzed', excludeFromAll: false
        sortDisplayName index: 'not_analyzed'
        only = ['id', 'sortDisplayName', 'displayName', 'username']
    }

    //keep grails from getting confused into thinking that these are opposite sides of the
    //same relationship
    static mappedBy = [createdBy: 'none', editedBy: 'none']

    String username
    String displayName = ''
    String email = ''
    String bio = ''
    // not sure why createdDate is listed here since it will get added by the AuditStamp
    Avatar avatar
    String uuid

    //Essentially to track if the current user is a user, admin or external admin
    String userRoles

    def beforeInsert() {
        if (!uuid) {
            uuid = Utils.generateUUID();
        }
    }

    transient String sortDisplayName

    static constraints = {
        username(blank: false, nullable: false, unique: true, maxSize: 256)
        displayName(nullable: true, maxSize: 256)
        email(nullable: true, maxSize: 256)
        bio(nullable: true, maxSize: 1000)
        avatar(nullable: true)
        uuid(nullable:true, unique: true)
        userRoles(nullable: true)
    }

    static mapping = {
        cache true
        tablePerHierarchy false
    }

    static transients = ['sortDisplayName']

    static final String SYSTEM_USER_NAME = 'System'

    static Profile getSystemUser() {
        Profile.findByUsername(Profile.SYSTEM_USER_NAME)
    }

    String display() {
        // Use this non-Groovy construct to avoid Groovy boolean casting, which is inefficient (https://jira.codehaus.org/browse/GROOVY-5059) (MS)
        return (displayName?.size() > 0) ? displayName : username
    }

    String toString() {
        return display()
    }

    String prettyPrint() {
        return display()
    }

    void scrubCR() {
        if (this.bio) {
            this.bio = this.bio.replaceAll("\r", "")
        }
    }

    @Override
    JSONObject asJSON() {
        UserDomainInstance userDomainInstance = this.userDomainInstance

        marshall([id               : id,
                  uuid             : uuid,
                  username         : username,
                  displayName      : displayName,
                  email            : email,
                  bio              : bio,
                  class            : getClass().toString(),
                  theme            : userDomainInstance?.theme,
                  animationsEnabled: userDomainInstance?.animationsEnabled])
    }

    def asJSONRef() {
        def currJSON = new JSONObject(
            id: id,
            username: username,
            name: display()
        )
        return currJSON
    }

    def bindFromJSON(JSONObject json) {
        [
            "username",
            "displayName",
            "email",
            "uuid",
            "bio"
        ].each(JS.optStr.curry(json, this))

        [
            "editedDate"
        ].each(JS.optDate.curry(json, this))
    }

    def afterLoad() {
        this.sortDisplayName = this.displayName?.toLowerCase()
    }

    @Override
    public int hashCode() {
        new HashCodeBuilder()
                .append(username)
                .toHashCode()
    }

    @Override
    public boolean equals(other) {
        // Since owners is a lazy loaded collection, the instances could be
        // hibernate proxies, so use the GORM 'instanceOf' method
        Boolean sameType
        try {
            sameType = other.instanceOf(Profile)
        } catch(MissingMethodException mme) {
            sameType = false
        }

        if(sameType) {
            return new EqualsBuilder()
                        .append(username, other.username)
                        .isEquals()
        }

        return false
    }

    private UserDomainInstance getUserDomainInstance() {
        UserDomainInstance.findByUsername(this.username)
    }
}
