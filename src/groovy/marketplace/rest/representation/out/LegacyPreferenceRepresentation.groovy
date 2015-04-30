package marketplace.rest.representation.out

import org.springframework.stereotype.Component

import marketplace.hal.ApplicationRootUriBuilderHolder
import marketplace.hal.AbstractHalRepresentation
import marketplace.hal.RepresentationFactory

import marketplace.rest.LegacyPreference

class LegacyPreferenceRepresentation extends AbstractHalRepresentation<LegacyPreference> {
    private LegacyPreference legacyPreference

    private LegacyPreferenceRepresentation(LegacyPreference legacyPreference) {
        this.legacyPreference = legacyPreference
    }

    public String getNamespace() { legacyPreference.namespace }
    public String getName() { legacyPreference.name }
    public String getValue() { legacyPreference.value }
    public String getUser() { legacyPreference.user.username }

    @Component
    public static class Factory implements RepresentationFactory<LegacyPreference> {
        AbstractHalRepresentation<LegacyPreference> toRepresentation(
                LegacyPreference legacyPreference,
                ApplicationRootUriBuilderHolder uriBuilderHolder) {
            new LegacyPreferenceRepresentation(legacyPreference)
        }
    }
}
