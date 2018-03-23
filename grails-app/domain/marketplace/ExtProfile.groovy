package marketplace

import org.grails.web.json.JSONObject

import marketplace.JSONUtil as JS


class ExtProfile extends Profile implements ToJSON {

    String systemUri
    String externalId
    String externalViewUrl
    String externalEditUrl

    static constraints = {
        systemUri(nullable: false)

        externalId(unique: 'systemUri', nullable: true)

        externalViewUrl(nullable: true, maxSize: Constants.MAX_URL_SIZE, validator: {
            val, obj ->
                // lets make sure it's an http or https URL here
                if (val != null && val.trim().size() > 0 && !val.matches(/http(s*)(:\/\/)(.*)/)) {
                    return ['externalContext.externalViewUrl.url.invalid']
                }
        })
        externalEditUrl(nullable: true, maxSize: Constants.MAX_URL_SIZE, validator: {
            val, obj ->
                // lets make sure it's an http or https URL here
                if (val != null && val.trim().size() > 0 && !val.matches(/http(s*)(:\/\/)(.*)/)) {
                    return ['externalContext.externalViewUrl.url.invalid']
                }
        })
    }

    public String toExpandedString() {
        return "${super.toString()} || ${systemUri}, ${externalId}, ${externalViewUrl}, ${externalEditUrl}"
    }

    @Override
    JSONObject asJSON() {
        def json = super.asJSON()
        json.putAll([systemUri      : systemUri,
                     externalId     : externalId,
                     externalViewUrl: externalViewUrl,
                     externalEditUrl: externalEditUrl,
                     id             : id])
        json
    }

    def bindFromJSON(JSONObject json) {
        super.bindFromJSON(json)
        [
            "systemUri",
            "externalId",
            "externalViewUrl",
            "externalEditUrl"
        ].each(JS.optStr.curry(json, this))
    }
}
