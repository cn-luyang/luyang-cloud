package io.github.luyang.platform.uaa.strategy.authenticator;

import io.github.luyang.platform.uaa.common.enums.ErrorCode;
import io.github.luyang.platform.uaa.common.enums.LoginMethod;
import io.github.luyang.platform.uaa.strategy.authenticator.handler.PasswordAuthenticatorHandler;
import io.github.luyang.starter.base.util.SpringUtil;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录认证上下文
 *
 * @author yang.lu
 */
@UtilityClass
public class AuthenticatorContext {

	private static final Map<LoginMethod, AuthenticatorHandler> AUTH_POOL = new ConcurrentHashMap<>();

	static {
		AUTH_POOL.put(LoginMethod.PASSWORD, SpringUtil.getBean(PasswordAuthenticatorHandler.class));
	}

	/**
	 * 获取认证处理器
	 *
	 * @param LoginMethod 登录方式
	 * @return 认证处理器
	 * @author yang.lu
	 */
	public static AuthenticatorHandler getAuthenticator(LoginMethod LoginMethod) {
		AuthenticatorHandler authenticatorHandler = AUTH_POOL.get(LoginMethod);
		ErrorCode.LOGIN_METHOD_UNSUPPORTED.notNull(authenticatorHandler);
		return authenticatorHandler;
	}
}
