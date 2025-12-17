package io.github.luyang.platform.uaa.code.beans;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * OAuth2 授权码 Domain
 *
 * @author yang.lu
 */
public record OAuth2CodeDomain(
	String code,
	String clientId,
	String userId,
	Set<String> scopes,
	String redirectUri,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod,
	LocalDateTime expiresTime,
	Boolean used,
	LocalDateTime usedTime
) {

	public boolean isValidCode() {
		return BooleanUtil.isFalse(used) && expiresTime.isAfter(LocalDateTime.now());
	}
}
