package io.github.luyang.platform.uaa.beans.contract;

import java.util.List;

/**
 * @author yang.lu
 */
public record ClientDomain(
	String clientId,
	String clientName,
	List<String> redirectUris,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	String description
) {

	public boolean isValidRedirectUri(String redirectUri) {
		return redirectUris != null && redirectUris.contains(redirectUri);
	}
}
