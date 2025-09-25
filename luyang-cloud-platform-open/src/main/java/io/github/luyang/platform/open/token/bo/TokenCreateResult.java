package io.github.luyang.platform.open.token.bo;

/**
 * @author yang.lu
 */
public record TokenCreateResult(
	String accessToken,
	String refreshToken
) {
}
