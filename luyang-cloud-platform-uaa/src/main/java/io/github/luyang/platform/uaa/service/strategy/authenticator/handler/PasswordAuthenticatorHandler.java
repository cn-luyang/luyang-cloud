package io.github.luyang.platform.uaa.service.strategy.authenticator.handler;

import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.platform.uaa.service.strategy.authenticator.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 密码认证处理器
 *
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@Override
	public String authenticate(Map<String, Object> maps) {

		String account = (String) maps.get("account");
		String credential = (String) maps.get("credential");

		AccountAuthRequest.builder()
			.account(account)
			.credential(credential)
			.build();

		return "";
	}
}
