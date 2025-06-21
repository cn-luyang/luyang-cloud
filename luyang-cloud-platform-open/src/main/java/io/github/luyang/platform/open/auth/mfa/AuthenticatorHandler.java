package io.github.luyang.platform.open.auth.mfa;

import io.github.luyang.platform.open.auth.controller.request.LoginReq;

/**
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	String authenticate(LoginReq loginReq);
}
