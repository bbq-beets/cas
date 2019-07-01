package org.apereo.cas.webauthn.web.flow;

import org.apereo.cas.webauthn.credential.repository.WebAuthnCredentialRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
 * This is {@link WebAuthnAccountCheckRegistrationAction}.
 *
 * @author Misagh Moayyed
 * @since 6.1.0
 */
@RequiredArgsConstructor
public class WebAuthnAccountCheckRegistrationAction extends AbstractAction {
    private final WebAuthnCredentialRepository webAuthnCredentialRepository;

    @Override
    protected Event doExecute(final RequestContext requestContext) throws Exception {
        return null;
    }
}
