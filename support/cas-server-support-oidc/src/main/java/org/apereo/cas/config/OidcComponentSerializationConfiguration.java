package org.apereo.cas.config;

import org.apereo.cas.authentication.principal.OidcPairwisePersistentIdGenerator;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.configuration.features.CasFeatureModule;
import org.apereo.cas.oidc.claims.OidcAddressScopeAttributeReleasePolicy;
import org.apereo.cas.oidc.claims.OidcAssuranceScopeAttributeReleasePolicy;
import org.apereo.cas.oidc.claims.OidcCustomScopeAttributeReleasePolicy;
import org.apereo.cas.oidc.claims.OidcEmailScopeAttributeReleasePolicy;
import org.apereo.cas.oidc.claims.OidcPhoneScopeAttributeReleasePolicy;
import org.apereo.cas.oidc.claims.OidcProfileScopeAttributeReleasePolicy;
import org.apereo.cas.oidc.ticket.OidcDefaultPushedAuthorizationRequest;
import org.apereo.cas.oidc.ticket.OidcPushedAuthorizationRequest;
import org.apereo.cas.services.OidcRegisteredService;
import org.apereo.cas.services.PairwiseOidcRegisteredServiceUsernameAttributeProvider;
import org.apereo.cas.ticket.serialization.TicketSerializationExecutionPlanConfigurer;
import org.apereo.cas.util.serialization.AbstractJacksonBackedStringSerializer;
import org.apereo.cas.util.serialization.ComponentSerializationPlanConfigurer;
import org.apereo.cas.util.spring.boot.ConditionalOnFeatureEnabled;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ScopedProxyMode;

import java.io.Serial;

/**
 * This is {@link OidcComponentSerializationConfiguration}.
 *
 * @author Misagh Moayyed
 * @since 6.1.0
 */
@EnableConfigurationProperties(CasConfigurationProperties.class)
@ConditionalOnFeatureEnabled(feature = CasFeatureModule.FeatureCatalog.OpenIDConnect)
@AutoConfiguration
public class OidcComponentSerializationConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "oidcTicketSerializationExecutionPlanConfigurer")
    @RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
    public TicketSerializationExecutionPlanConfigurer oidcTicketSerializationExecutionPlanConfigurer() {
        return plan -> {
            plan.registerTicketSerializer(new OidcPushedAuthorizationRequestSerializer());
            plan.registerTicketSerializer(OidcPushedAuthorizationRequest.class.getName(),
                new OidcPushedAuthorizationRequestSerializer());
        };
    }

    @Bean
    @ConditionalOnMissingBean(name = "oidcComponentSerializationPlanConfigurer")
    @RefreshScope(proxyMode = ScopedProxyMode.DEFAULT)
    public ComponentSerializationPlanConfigurer oidcComponentSerializationPlanConfigurer() {
        return plan -> {
            plan.registerSerializableClass(PairwiseOidcRegisteredServiceUsernameAttributeProvider.class);
            plan.registerSerializableClass(OidcRegisteredService.class);
            plan.registerSerializableClass(OidcPairwisePersistentIdGenerator.class);
            plan.registerSerializableClass(OidcDefaultPushedAuthorizationRequest.class);

            plan.registerSerializableClass(OidcAddressScopeAttributeReleasePolicy.class);
            plan.registerSerializableClass(OidcCustomScopeAttributeReleasePolicy.class);
            plan.registerSerializableClass(OidcEmailScopeAttributeReleasePolicy.class);
            plan.registerSerializableClass(OidcPhoneScopeAttributeReleasePolicy.class);
            plan.registerSerializableClass(OidcAssuranceScopeAttributeReleasePolicy.class);
            plan.registerSerializableClass(OidcProfileScopeAttributeReleasePolicy.class);
        };
    }

    private static final class OidcPushedAuthorizationRequestSerializer extends
        AbstractJacksonBackedStringSerializer<OidcDefaultPushedAuthorizationRequest> {
        @Serial
        private static final long serialVersionUID = -6298623586274810263L;

        @Override
        public Class<OidcDefaultPushedAuthorizationRequest> getTypeToSerialize() {
            return OidcDefaultPushedAuthorizationRequest.class;
        }
    }
}
