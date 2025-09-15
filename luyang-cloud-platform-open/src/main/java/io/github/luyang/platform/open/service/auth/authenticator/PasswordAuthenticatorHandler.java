package io.github.luyang.platform.open.service.auth.authenticator;

import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.open.beans.enums.error.LoginError;
import io.github.luyang.platform.open.beans.request.LoginReq;
import io.github.luyang.platform.open.service.auth.AuthenticatorHandler;
import io.github.luyang.starter.base.api.Result;
import io.github.luyang.starter.base.api.ResultOps;
import io.github.luyang.starter.base.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@DubboReference
	private RemoteUserService remoteUserService;

	@Override
	public AccountAuthResponse authenticate(LoginReq loginReq) {

		AccountAuthRequest accountAuthRequest = new AccountAuthRequest(loginReq.account(), loginReq.credential());
		Result<AccountAuthResponse> accountAuthDTOResult = remoteUserService.accountAuth(accountAuthRequest);

		return ResultOps.of(accountAuthDTOResult)
			.assertSuccess(() -> new BusinessException(accountAuthDTOResult.getCode(), accountAuthDTOResult.getMessage()))
			.getData()
			.orElseThrow(() -> new BusinessException(LoginError.INVALID_ACCOUNT_OR_PASSWORD));
	}
}
