package io.github.luyang.platform.open.auth.mfa;

import io.github.luyang.platform.open.auth.mfa.authenticator.PasswordAuthenticatorHandler;
import io.github.luyang.platform.open.base.enums.GrantType;
import io.github.luyang.starter.web.util.SpringUtil;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yang.lu
 */
@UtilityClass
public class AuthenticatorContext {

	private static final Map<GrantType, AuthenticatorHandler> AUTH_POOL = new ConcurrentHashMap<>();

	static {
		AUTH_POOL.put(GrantType.PASSWORD, SpringUtil.getBean(PasswordAuthenticatorHandler.class));
	}

	/**
	 * 获取指定的登录实例
	 *
	 * @param loginType 登录类型
	 * @return MultiFactorAuthenticator<T> 登录处理
	 * @author yang.lu
	 */
	public static AuthenticatorHandler getAuthenticator(GrantType loginType) {
		AuthenticatorHandler authenticator = AUTH_POOL.get(loginType);
		return AUTH_POOL.get(loginType);
	}
}
