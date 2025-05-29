package io.github.luyang.platform.open.auth.mfa.authenticator;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@Override
	public String authenticate(LoginRequest request) {
		// TODO 远程调用获取用户信息、密码比对
		return "";
	}
}
