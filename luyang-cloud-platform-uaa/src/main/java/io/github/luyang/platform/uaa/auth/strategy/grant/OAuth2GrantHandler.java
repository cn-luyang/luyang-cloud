package io.github.luyang.platform.uaa.auth.strategy.grant;

import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenRequest;
import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenResponse;

/**
 * 授权处理器接口
 *
 * @author yang.lu
 */
public interface OAuth2GrantHandler {

	/**
	 * 执行授权流程
	 *
	 * @param request 授权请求
	 * @return 授权响应
	 * @author yang.lu
	 */
	ApplyTokenResponse handle(ApplyTokenRequest request);

}
