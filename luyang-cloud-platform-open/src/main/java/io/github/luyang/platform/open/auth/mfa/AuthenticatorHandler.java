package io.github.luyang.platform.open.auth.mfa;

import io.github.luyang.api.uac.dto.AccountAuthDTO;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;

/**
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	AccountAuthDTO authenticate(LoginRequest loginRequest);
}
