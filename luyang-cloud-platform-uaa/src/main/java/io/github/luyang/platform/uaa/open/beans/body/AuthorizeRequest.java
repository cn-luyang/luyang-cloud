package io.github.luyang.platform.uaa.open.beans.body;

/**
 * @author yang.lu
 */
public record AuthorizeRequest(
	String responseType,
	String clientId,
	String redirectUri,
	String scope,
	String state,
	String codeChallenge,
	String codeChallengeMethod
) {
}
