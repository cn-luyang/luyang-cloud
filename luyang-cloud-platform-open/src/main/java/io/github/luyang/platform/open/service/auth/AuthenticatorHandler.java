package io.github.luyang.platform.open.service.auth;

import io.github.luyang.platform.open.beans.request.LoginRequest;

/**
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	void authenticate(LoginRequest loginRequest);
}
