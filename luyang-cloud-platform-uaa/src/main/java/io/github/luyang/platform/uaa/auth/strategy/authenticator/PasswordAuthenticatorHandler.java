package io.github.luyang.platform.uaa.auth.strategy.authenticator;

import cn.hutool.core.bean.BeanUtil;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa.auth.beans.bo.PasswordLoginParam;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

//	@DubboReference
//	private RemoteUserService remoteUserService;

	@Override
	public AccountAuthResponse authenticate(Map<String, Object> maps) {

		PasswordLoginParam loginParam = BeanUtil.toBean(maps, PasswordLoginParam.class);

		AccountAuthRequest accountAuthRequest = new AccountAuthRequest(loginParam.getAccount(), loginParam.getPassword());
//		Result<AccountAuthResponse> accountAuthDTOResult = remoteUserService.accountAuth(accountAuthRequest);

//		return ResultOps.of(accountAuthDTOResult).getData();
		return null;
	}
}
