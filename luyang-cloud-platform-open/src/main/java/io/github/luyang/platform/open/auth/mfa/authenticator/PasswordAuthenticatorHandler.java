package io.github.luyang.platform.open.auth.mfa.authenticator;

import cn.hutool.core.bean.BeanUtil;
import io.github.luyang.api.uac.UserServiceRpc;
import io.github.luyang.api.uac.dto.AccountAuthDTO;
import io.github.luyang.api.uac.param.AccountAuthParam;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.enums.error.LoginError;
import io.github.luyang.starter.base.api.Result;
import io.github.luyang.starter.base.api.ResultOps;
import io.github.luyang.starter.base.error.BusinessException;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 密码认证
 *
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@DubboReference(check = false)
	private UserServiceRpc userServiceRpc;

	@Override
	public Map<String, Object> authenticate(LoginRequest loginRequest) {

		AccountAuthParam accountAuthParam = new AccountAuthParam(loginRequest.account(), loginRequest.secret());
		Result<AccountAuthDTO> accountAuthDTOResult = userServiceRpc.accountAuth(accountAuthParam);

		AccountAuthDTO accountAuthDTO = ResultOps.of(accountAuthDTOResult)
			.assertSuccess(() -> new BusinessException(accountAuthDTOResult.getCode(), accountAuthDTOResult.getMessage()))
			.getData()
			.orElseThrow(() -> new BusinessException(LoginError.INVALID_ACCOUNT_OR_PASSWORD));

		return BeanUtil.beanToMap(accountAuthDTO);
	}
}
