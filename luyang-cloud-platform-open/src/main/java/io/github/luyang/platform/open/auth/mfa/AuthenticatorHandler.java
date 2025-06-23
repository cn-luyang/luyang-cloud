package io.github.luyang.platform.open.auth.mfa;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;

import java.util.Map;

/**
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	Map<String, Object> authenticate(LoginRequest loginRequest);
}
