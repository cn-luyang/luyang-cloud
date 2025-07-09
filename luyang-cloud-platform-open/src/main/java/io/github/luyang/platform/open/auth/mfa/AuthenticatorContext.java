package io.github.luyang.platform.open.auth.mfa;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.open.auth.mfa.authenticator.PasswordAuthenticatorHandler;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.base.enums.error.LoginError;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
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
