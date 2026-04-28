package io.github.luyang.platform.uaa.beans.payload;

import java.util.Set;

public record CreateClientDTO(
	String clientName,
	Set<String> redirectUris,
	int accessTokenValidity,
	int refreshTokenValidity,
	int authorizationCodeValidity,
	String description
) {
}
