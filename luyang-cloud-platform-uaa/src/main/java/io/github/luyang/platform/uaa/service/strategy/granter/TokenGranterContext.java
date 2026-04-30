package io.github.luyang.platform.uaa.service.strategy.granter;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uaa.common.enums.ErrorCode;
import io.github.luyang.platform.uaa.common.enums.GrantType;
import io.github.luyang.platform.uaa.service.strategy.granter.handler.AuthorizationCodeGrantHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 授权模式上下文
 *
 * @author yang.lu
 */
public final class TokenGranterContext {

	private static final Map<GrantType, TokenGranterHandler> GRANT_POOL = new ConcurrentHashMap<>();

	private TokenGranterContext() {
	}

	static {
		GRANT_POOL.put(GrantType.AUTHORIZATION_CODE, SpringUtil.getBean(AuthorizationCodeGrantHandler.class));
	}

	/**
	 * 获取授权处理器
	 *
	 * @param grantType 授权类型
	 * @return 授权处理器
	 * @author yang.lu
	 */
	public static TokenGranterHandler getOAuth2GrantHandler(GrantType grantType) {
		TokenGranterHandler granterHandler = GRANT_POOL.get(grantType);
		ErrorCode.AUTH_OAUTH_GRANT_TYPE_UNSUPPORTED.notNull(granterHandler);
		return granterHandler;
	}
}
