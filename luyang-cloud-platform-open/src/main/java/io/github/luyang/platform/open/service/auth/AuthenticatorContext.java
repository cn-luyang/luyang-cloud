package io.github.luyang.platform.open.service.auth;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.open.beans.enums.LoginType;
import io.github.luyang.platform.open.beans.enums.error.LoginError;
import io.github.luyang.platform.open.service.auth.authenticator.PasswordAuthenticatorHandler;
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
	 * 获取指定的登录实例
	 *
	 * @param loginType 登录类型
	 * @return MultiFactorAuthenticator<T> 登录处理
	 * @author yang.lu
	 */
	public static AuthenticatorHandler getAuthenticator(LoginType loginType) {
		AuthenticatorHandler authenticatorHandler = AUTH_POOL.get(loginType);
		// 如果认证处理器不存在，抛出 INVALID_GRANT 异常
		LoginError.INVALID_LOGIN_TYPE.notNull(authenticatorHandler);
		return authenticatorHandler;
	}
}
