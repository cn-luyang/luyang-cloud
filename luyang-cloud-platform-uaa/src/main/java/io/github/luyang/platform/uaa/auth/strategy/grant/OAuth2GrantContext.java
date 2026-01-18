package io.github.luyang.platform.uaa.auth.strategy.grant;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uaa._common.enums.dict.OAuth2GrantType;
import io.github.luyang.platform.uaa._common.enums.error.OAuth2ClientError;
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

	private static final Map<OAuth2GrantType, OAuth2GrantHandler> GRANT_POOL = new ConcurrentHashMap<>();

	static {
		GRANT_POOL.put(OAuth2GrantType.AUTHORIZATION_CODE, SpringUtil.getBean(AuthorizationCodeGrantHandler.class));
	}

	/**
	 * 获取授权处理器
	 *
	 * @param grantType 授权类型
	 * @return 授权处理器
	 * @author yang.lu
	 */
	public static OAuth2GrantHandler getOAuth2GrantHandler(OAuth2GrantType grantType) {
		OAuth2GrantHandler grantHandler = GRANT_POOL.get(grantType);
		OAuth2ClientError.UNSUPPORTED_GRANT_TYPE.notNull(grantHandler);
		return grantHandler;
	}
}
