package marketplace

import gorm.AuditStamp
import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder
import org.codehaus.groovy.grails.web.json.JSONObject

import marketplace.Listing

import marketplace.converter.UUIDConverter

/**
 * A Domain class representing a pair of screenshots.  This pair should consist of
 * a large version and a small version of the same image.  The large version is optional.
 * If it is not present, the small version should be used for both
 */
@AuditStamp
class Screenshot implements Serializable {

    static searchable = {
        root false
        largeImageId index: 'not_analyzed', excludeFromAll: true, converter: UUIDConverter
        smallImageId index: 'not_analyzed', excludeFromAll: true, converter: UUIDConverter
        only = ['largeImageId', 'smallImageId']
    }

    static belongsTo = [serviceItem: Listing]

    UUID largeImageId
    UUID smallImageId

    static constraints = {
        smallImageId nullable: true, validator: requiredUnlessInProgress
        largeImageId nullable: true, validator: requiredUnlessInProgress
    }

    static mapping = {
        smallImageId sqlType: "binary", length: 16
        largeImageId sqlType: "binary", length: 16
    }

    public UUID getLargeImageId() {
        this.largeImageId ? this.largeImageId : this.smallImageId
    }



    //A closure to use to validate that properties are present on a listing
    //that is in any approvalStatus other than IN_PROGRESS
    static requiredUnlessInProgress = { val, Screenshot screenshot ->
        (screenshot.serviceItem.approvalStatus != ApprovalStatus.IN_PROGRESS &&     //reject if not draft
            (val == null ||                                          //and val is null
                (val.respondsTo('size') && val.size() == 0)          //or empty/blank
            )
        ) ? "requiredUnlessInProgress" : true
    }

    @Override
    boolean equals(other) {
        // Since screenshots is a lazy loaded collection, the instances could be
        // hibernate proxies, so use the GORM 'instanceOf' method
        Boolean sameType
        try {
            sameType = other.instanceOf(Screenshot)
        } catch(MissingMethodException mme) {
            sameType = false
        }

        if(sameType) {
            return new EqualsBuilder()
                        .append(smallImageId, other.smallImageId)
                        .append(largeImageId, other.largeImageId)
                        .isEquals()
        }
        return false
    }

    @Override
    int hashCode() {
        return new HashCodeBuilder()
                    .append(smallImageId)
                    .append(largeImageId)
                    .toHashCode()
    }
}
