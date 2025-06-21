package io.github.luyang.platform.open.auth.mfa.authenticator;

import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.model.VerifyAccountParam;
import io.github.luyang.api.uac.model.VerifyAccountResult;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.enums.error.LoginError;
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
	public String authenticate(LoginRequest request) {

		VerifyAccountParam verifyAccountParam = VerifyAccountParam.builder()
			.account(request.getAccount())
			.secret(request.getSecret())
			.build();

		Result<VerifyAccountResult> verifyAccountResult = remoteUserService.verifyAccount(verifyAccountParam);
		return ResultOps.of(verifyAccountResult)
			.assertSuccess(() -> new BusinessException(verifyAccountResult.getCode(), verifyAccountResult.getMessage()))
			.getData()
			.map(VerifyAccountResult::getUserId)
			.orElseThrow(() -> new BusinessException(LoginError.INVALID_ACCOUNT_OR_PASSWORD));
	}
}
