package io.github.luyang.platform.open.client;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * 客户端 Domain
 *
 * @param clientId             客户端ID
 * @param clientName           应用名
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @param redirectUris         重定向URI
 * @author yang.lu
 */
public record ClientDomain(
	String clientId,
	String clientName,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	List<String> redirectUris
) {

	/**
	 * 校验给定的 URI 是否在允许的回调地址中
	 *
	 * @param uri 要检查的URI
	 * @return 如果重定向URI为空，或者包含给定的URI，则返回 true，否则返回 false
	 * @author yang.lu
	 */
	public boolean isValidRedirectUri(String uri) {

		String uriHost = UrlBuilder.of(uri).getHost();

		return CollUtil.emptyIfNull(redirectUris).stream()
			.map(UrlBuilder::of)
			.map(UrlBuilder::getHost)
			.anyMatch(allowedHost -> StrUtil.equals(allowedHost, uriHost));
	}
}
