package io.github.luyang.platform.uaa.client.beans;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Set;

/**
 * 客户端 Domain
 *
 * @param clientId             客户端 ID
 * @param clientName           客户端名称
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @param redirectUris         授权回调地址列表
 * @param scopes               授权范围列表
 * @author yang.lu
 */
public record ClientDomain(
	String clientId,
	String clientName,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	Set<String> redirectUris,
	Set<String> scopes
) {

	/**
	 * 验证重定向 URI 是否合法
	 *
	 * @param uri 要检查的 URI
	 * @return 如果重定向URI为空，或者包含给定的URI，则返回 true，否则返回 false
	 * @author yang.lu
	 */
	public boolean isValidRedirectUri(String uri) {

		return CollUtil.emptyIfNull(redirectUris).stream()
			.filter(StrUtil::isNotBlank)
			.anyMatch(allowedUri -> StrUtil.startWith(allowedUri, uri));
	}
}
