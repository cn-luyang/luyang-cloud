package io.github.luyang.platform.uaa.auth.strategy;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa._common.enums.error.LoginError;
import io.github.luyang.platform.uaa.auth.strategy.authenticator.PasswordAuthenticatorHandler;
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

	private static final Map<LoginMethodEnum, AuthenticatorHandler> AUTH_POOL = new ConcurrentHashMap<>();

	static {
		AUTH_POOL.put(LoginMethodEnum.PASSWORD, SpringUtil.getBean(PasswordAuthenticatorHandler.class));
	}

	/**
	 * 获取指定的登录实例
	 *
	 * @param loginMethodEnum 登录方式
	 * @return MultiFactorAuthenticator<T> 登录处理
	 * @author yang.lu
	 */
	public static AuthenticatorHandler getAuthenticator(LoginMethodEnum loginMethodEnum) {
		AuthenticatorHandler authenticatorHandler = AUTH_POOL.get(loginMethodEnum);
		// 如果认证处理器不存在，抛出 INVALID_GRANT 异常
		LoginError.INVALID_LOGIN_TYPE.notNull(authenticatorHandler);
		return authenticatorHandler;
	}
}
