package org.apereo.cas.mfa.accepto.web.flow;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.configurer.AbstractMultifactorTrustedDeviceWebflowConfigurer;

import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * This is {@link AccepttoMultifactorTrustWebflowConfigurer}.
 *
 * @author Misagh Moayyed
 * @since 6.1.0
 */
public class AccepttoMultifactorTrustWebflowConfigurer extends AbstractMultifactorTrustedDeviceWebflowConfigurer {

    private final FlowDefinitionRegistry flowDefinitionRegistry;

    public AccepttoMultifactorTrustWebflowConfigurer(final FlowBuilderServices flowBuilderServices,
                                                final boolean deviceRegistrationEnabled,
                                                final FlowDefinitionRegistry loginFlowDefinitionRegistry,
                                                final ApplicationContext applicationContext,
                                                final CasConfigurationProperties casProperties) {
        super(flowBuilderServices, loginFlowDefinitionRegistry, deviceRegistrationEnabled, applicationContext, casProperties);
        flowDefinitionRegistry = loginFlowDefinitionRegistry;
    }

    @Override
    protected void doInitialize() {
        registerMultifactorTrustedAuthentication(this.flowDefinitionRegistry);
    }
}

