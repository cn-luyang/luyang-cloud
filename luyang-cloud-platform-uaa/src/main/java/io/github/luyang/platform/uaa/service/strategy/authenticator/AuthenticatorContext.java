package io.github.luyang.platform.uaa.service.strategy.authenticator;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uaa.common.enums.ErrorCode;
import io.github.luyang.platform.uaa.common.enums.LoginType;
import io.github.luyang.platform.uaa.service.strategy.authenticator.handler.PasswordAuthenticatorHandler;
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

	private static final Map<LoginType, AuthenticatorHandler> AUTH_POOL = new ConcurrentHashMap<>();

	static {
		AUTH_POOL.put(LoginType.PASSWORD, SpringUtil.getBean(PasswordAuthenticatorHandler.class));
	}

	/**
	 * 获取认证处理器
	 *
	 * @param loginType 登录方式
	 * @return 认证处理器
	 * @author yang.lu
	 */
	public static AuthenticatorHandler getAuthenticator(LoginType loginType) {
		AuthenticatorHandler authenticatorHandler = AUTH_POOL.get(loginType);
		ErrorCode.AUTH_LOGIN_TYPE_UNSUPPORTED.notNull(authenticatorHandler);
		return authenticatorHandler;
	}
}
