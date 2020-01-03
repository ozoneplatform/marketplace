package marketplace

import org.grails.web.json.JSONObject

import org.apache.commons.lang.builder.EqualsBuilder
import org.apache.commons.lang.builder.HashCodeBuilder


class ServiceItemTag implements Serializable, ToJSON {

    static belongsTo = [serviceItem: ServiceItem, tag: Tag, createdBy: Profile]

    static bindableProperties = ['serviceItem', 'tag', 'createdBy']

    static modifiableReferenceProperties = []

    static searchable = {
        root false
        tag component: true, excludeFromAll: false
        createdBy component: true, excludeFromAll: true
        only = ['tag', 'createdBy']
    }

    static mapping = {
        tag cascade: 'save-update'  //If we do delete then tag is removed when this item is removed
        tag index: 'service_item_tag_tag_idx'
        tag fetch: 'join'
        serviceItem index: 'service_item_tag_si_idx'
    }

    static constraints = {
        serviceItem(validator: { val, obj ->
            def tag = Tag.findByTitle(obj.tag.title)
            def serviceItemTag = ServiceItemTag.findByTagAndServiceItem(tag, obj.serviceItem)

            //if the serviceItemTag exists and isn't simply the same tag we are
            //currently validating
            return (serviceItemTag && !serviceItemTag.is(obj)) ? ['unique'] : true
        })
    }

    JSONObject asJSONwithCreatedBy() {
        new JSONObject([id       : id,
                        tag      : new JSONObject([id   : tag.id,
                                                   title: tag.title]),
                        createdBy: new JSONObject([id      : createdBy?.id,
                                                   username: createdBy?.username ])])
    }

    @Override
    JSONObject asJSON() {
        asJSONwithCreatedBy()
    }

    @Override
    int hashCode() {
        new HashCodeBuilder()
            .append(tag?.title)
            .append(serviceItem?.uuid)
            .toHashCode()
    }

    @Override
    boolean equals(other) {
        if (other instanceof ServiceItemTag) {
            return new EqualsBuilder()
                .append(tag.title, other.tag.title)
                .append(serviceItem?.uuid, other.serviceItem.uuid)
                .isEquals()
        }
        return false
    }

}
