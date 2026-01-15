package io.github.luyang.platform.uaa.token.beans.bo;

/**
 * @author yang.lu
 */
public record TokenCreateResult(
	String accessToken,
	String refreshToken
) {
}
