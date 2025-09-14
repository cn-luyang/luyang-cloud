package io.github.luyang.platform.open.service.auth.authenticator;

import io.github.luyang.platform.open.beans.request.LoginRequest;
import io.github.luyang.platform.open.service.auth.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@Override
	public void authenticate(LoginRequest loginRequest) {

	}
}
