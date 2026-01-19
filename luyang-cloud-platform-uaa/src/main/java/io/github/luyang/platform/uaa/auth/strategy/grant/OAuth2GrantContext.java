package io.github.luyang.platform.uaa.auth.strategy.grant;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uaa._common.enums.GrantTypeEnum;
import io.github.luyang.platform.uaa._common.enums.infra.ErrorCode;
import io.github.luyang.platform.uaa.auth.strategy.grant.handler.AuthorizationCodeGrantHandler;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * OAuth2 授权模式上下文
 *
 * @author yang.lu
 */
@UtilityClass
public class OAuth2GrantContext {

	private static final Map<GrantTypeEnum, OAuth2GrantHandler> GRANT_POOL = new ConcurrentHashMap<>();

	static {
		GRANT_POOL.put(GrantTypeEnum.AUTHORIZATION_CODE, SpringUtil.getBean(AuthorizationCodeGrantHandler.class));
	}

	/**
	 * 获取授权处理器
	 *
	 * @param grantTypeEnum 授权类型
	 * @return 授权处理器
	 * @author yang.lu
	 */
	public static OAuth2GrantHandler getOAuth2GrantHandler(GrantTypeEnum grantTypeEnum) {
		OAuth2GrantHandler grantHandler = GRANT_POOL.get(grantTypeEnum);
		ErrorCode.LOGIN_METHOD_UNSUPPORTED.notNull(grantHandler);
		return grantHandler;
	}
}
