package io.github.luyang.platform.uaa.token.beans.bo;

/**
 * @author yang.lu
 */
public record OAuth2TokenCreateResult(
	String accessToken,
	String refreshToken
) {
}
