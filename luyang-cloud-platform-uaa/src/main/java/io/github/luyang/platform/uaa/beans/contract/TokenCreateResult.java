package io.github.luyang.platform.uaa.beans.contract;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
public record TokenCreateResult(
	String accessToken,
	String refreshToken,
	LocalDateTime accessTokenExpiresTime
) {
}
