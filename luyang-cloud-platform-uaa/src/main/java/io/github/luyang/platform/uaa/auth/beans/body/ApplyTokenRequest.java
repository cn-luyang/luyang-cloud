package io.github.luyang.platform.uaa.auth.beans.body;

public record ApplyTokenRequest(
	String grantType,
	String code,
	String redirectUri,
	String clientId,
	String codeVerifier,
	String refreshToken
) {
}
