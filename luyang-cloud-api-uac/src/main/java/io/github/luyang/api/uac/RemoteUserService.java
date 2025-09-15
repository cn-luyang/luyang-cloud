package io.github.luyang.api.uac;

import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.starter.base.api.Result;

/**
 * @author yang.lu
 */
public interface RemoteUserService {

	Result<AccountAuthResponse> accountAuth(AccountAuthRequest accountAuthRequest);
}
