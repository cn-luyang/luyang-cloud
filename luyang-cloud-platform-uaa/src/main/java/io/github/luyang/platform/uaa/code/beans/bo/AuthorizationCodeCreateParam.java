package io.github.luyang.platform.uaa.code.beans.bo;

import java.time.LocalDateTime;
import java.util.Set;

public record AuthorizationCodeCreateParam(
	String clientId,
	String userId,
	Set<String> scopes,
	String redirectUri,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod,
	LocalDateTime expiresTime
) {
}
