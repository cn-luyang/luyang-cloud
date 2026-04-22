package io.github.luyang.platform.uac.remote;

import io.github.luyang.api.uac.UserRemoteService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.starter.base.model.Result;

/**
 * @author yang.lu
 */
public class UserRemoteServiceImpl implements UserRemoteService {

	@Override
	public Result<AccountAuthResponse> accountAuth(AccountAuthRequest accountAuthRequest) {
		return null;
	}
}
