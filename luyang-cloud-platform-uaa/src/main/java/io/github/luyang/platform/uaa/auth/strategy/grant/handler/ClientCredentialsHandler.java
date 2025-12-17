package io.github.luyang.platform.uaa.auth.strategy.grant.handler;

import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenRequest;
import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenResponse;
import io.github.luyang.platform.uaa.auth.strategy.grant.OAuth2GrantHandler;
import org.springframework.stereotype.Component;

/**
 * 客户端凭证模式处理器
 *
 * @author yang.lu
 */
@Component
public class ClientCredentialsHandler implements OAuth2GrantHandler {
	@Override
	public ApplyTokenResponse handle(ApplyTokenRequest request) {
		return null;
	}
}
