package io.github.luyang.platform.uaa.code.beans.bo;

public record OAuth2CodeCreateParam(
	String clientId,
	String userId,
	String scope,
	String redirectUri,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod
) {
}
