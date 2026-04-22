package io.github.luyang.platform.uaa.auth.strategy.authenticator;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa._common.enums.infra.ErrorCode;
import io.github.luyang.platform.uaa.auth.strategy.authenticator.handler.PasswordAuthenticatorHandler;
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
	 * 获取认证处理器
	 *
	 * @param loginMethodEnum 登录方式
	 * @return 认证处理器
	 * @author yang.lu
	 */
	public static AuthenticatorHandler getAuthenticator(LoginMethodEnum loginMethodEnum) {
		AuthenticatorHandler authenticatorHandler = AUTH_POOL.get(loginMethodEnum);
		ErrorCode.LOGIN_METHOD_UNSUPPORTED.notNull(authenticatorHandler);
		return authenticatorHandler;
	}
}
