package io.github.luyang.platform.open.service.auth;

import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.open.beans.request.LoginReq;

/**
 * 认证处理器接口
 *
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	AccountAuthResponse authenticate(LoginReq loginReq);
}
