package io.github.luyang.platform.uaa.auth.strategy.authenticator.handler;

import cn.hutool.core.bean.BeanUtil;
import io.github.luyang.api.uac.UserRemoteService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa.auth.beans.bo.PasswordLoginParam;
import io.github.luyang.platform.uaa.auth.strategy.authenticator.AuthenticatorHandler;
import io.github.luyang.starter.base.model.Result;
import io.github.luyang.starter.base.model.ResultOps;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
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

	@DubboReference
	private UserRemoteService userRemoteService;

	@Override
	public AccountAuthResponse authenticate(Map<String, Object> maps) {

		PasswordLoginParam loginParam = BeanUtil.toBean(maps, PasswordLoginParam.class);

		AccountAuthRequest accountAuthRequest = new AccountAuthRequest(loginParam.getAccount(), loginParam.getPassword());
		Result<AccountAuthResponse> accountAuthDTOResult = userRemoteService.accountAuth(accountAuthRequest);
		return ResultOps.of(accountAuthDTOResult).getData();
	}
}
