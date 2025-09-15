package io.github.luyang.platform.open.service.auth;

import io.github.luyang.api.uac.response.AccountAuthResponse;

import java.util.Map;

/**
 * 认证处理器接口
 *
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	AccountAuthResponse authenticate(Map<String, Object> maps);
}
