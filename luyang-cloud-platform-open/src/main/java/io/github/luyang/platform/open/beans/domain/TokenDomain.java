package io.github.luyang.platform.open.beans.domain;

/**
 * @param accessToken  访问令牌
 * @param refreshToken 刷新令牌
 * @author yang.lu
 */
public record TokenDomain(
	String accessToken,
	String refreshToken
) {
}
