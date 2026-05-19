package io.github.luyang.platform.uaa.service.strategy.authenticator.handler;

import io.github.luyang.api.uac.AccountFeignClient;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa.service.strategy.authenticator.AuthenticatorHandler;
import io.github.luyang.starter.base.model.Result;
import io.github.luyang.starter.base.model.ResultOps;
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

	private final AccountFeignClient accountFeignClient;

	@Override
	public String authenticate(Map<String, Object> maps) {

		String account = (String) maps.get("account");
		String credential = (String) maps.get("credential");

		AccountAuthRequest accountAuthRequest = AccountAuthRequest.builder()
			.account(account)
			.credential(credential)
			.build();

		return ResultOps.of(accountFeignClient.accountAuth(accountAuthRequest))
			.getData()
			.userId();
	}
}
