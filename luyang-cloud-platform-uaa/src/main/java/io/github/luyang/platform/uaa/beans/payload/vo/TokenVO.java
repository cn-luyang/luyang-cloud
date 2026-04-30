package io.github.luyang.platform.uaa.beans.payload.vo;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
public record TokenVO(
	String accessToken,
	String tokenType,
	LocalDateTime expiresIn,
	String refreshToken,
	String idToken
) {
}
