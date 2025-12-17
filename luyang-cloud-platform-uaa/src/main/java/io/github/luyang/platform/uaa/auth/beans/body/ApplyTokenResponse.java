package io.github.luyang.platform.uaa.auth.beans.body;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author yang.lu
 */
public record ApplyTokenResponse(
	String idToken,
	String accessToken,
	String refreshToken,
	String tokenType,
	LocalDateTime expiresIn,
	Set<String> scopes
) {
}
