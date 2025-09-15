package io.github.luyang.platform.open.service.auth.authenticator;

import cn.hutool.core.bean.BeanUtil;
import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.open.beans.enums.error.LoginError;
import io.github.luyang.platform.open.beans.param.PasswordLoginParam;
import io.github.luyang.platform.open.service.auth.AuthenticatorHandler;
import io.github.luyang.starter.base.api.Result;
import io.github.luyang.starter.base.api.ResultOps;
import io.github.luyang.starter.base.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@DubboReference
	private RemoteUserService remoteUserService;

	@Override
	public AccountAuthResponse authenticate(Map<String, Object> maps) {

		PasswordLoginParam loginParam = BeanUtil.toBean(maps, PasswordLoginParam.class);

		AccountAuthRequest accountAuthRequest = new AccountAuthRequest(loginParam.getAccount(), loginParam.getCredential());
		Result<AccountAuthResponse> accountAuthDTOResult = remoteUserService.accountAuth(accountAuthRequest);

		return ResultOps.of(accountAuthDTOResult)
			.assertSuccess(() -> new BusinessException(accountAuthDTOResult.getCode(), accountAuthDTOResult.getMessage()))
			.getData()
			.orElseThrow(() -> new BusinessException(LoginError.INVALID_ACCOUNT_OR_PASSWORD));
	}
}
