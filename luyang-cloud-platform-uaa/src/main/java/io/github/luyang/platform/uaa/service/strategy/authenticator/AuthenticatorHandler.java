package io.github.luyang.platform.uaa.service.strategy.authenticator;

import java.util.Map;

/**
 * 认证处理器接口
 *
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	String authenticate(Map<String, Object> maps);
}
