package io.github.luyang.platform.uaa.auth.strategy.authenticator;

import cn.hutool.core.bean.BeanUtil;
import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa._common.enums.error.LoginError;
import io.github.luyang.platform.uaa.auth.beans.bo.PasswordLoginParam;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorHandler;
import io.github.luyang.starter.base.common.exception.BusinessException;
import io.github.luyang.starter.base.common.model.Result;
import io.github.luyang.starter.base.common.model.ResultOps;
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

		AccountAuthRequest accountAuthRequest = new AccountAuthRequest(loginParam.getAccount(), loginParam.getPassword());
		Result<AccountAuthResponse> accountAuthDTOResult = remoteUserService.accountAuth(accountAuthRequest);

		return ResultOps.of(accountAuthDTOResult)
			.assertSuccess(() -> new BusinessException(accountAuthDTOResult.getCode(), accountAuthDTOResult.getMessage()))
			.getData()
			.orElseThrow(() -> new BusinessException(LoginError.INVALID_ACCOUNT_OR_PASSWORD));
	}
}
