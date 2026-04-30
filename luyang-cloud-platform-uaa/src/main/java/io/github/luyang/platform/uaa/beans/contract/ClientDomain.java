package io.github.luyang.platform.uaa.beans.contract;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

/**
 * @author yang.lu
 */
public record ClientDomain(
	String clientId,
	String clientName,
	List<String> redirectUris,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	String description
) {

	public boolean isValidRedirectUri(String uri) {
		return CollUtil.emptyIfNull(redirectUris).stream()
			.filter(StrUtil::isNotBlank)
			.anyMatch(allowedUri -> StrUtil.startWith(allowedUri, uri));
	}
}
