package io.github.luyang.platform.uaa.beans.payload;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
public record LoginVO(
	String userId,
	String cnName,
	String accessToken,
	String refreshToken,
	LocalDateTime accessTokenExpiresTime
) {
}
