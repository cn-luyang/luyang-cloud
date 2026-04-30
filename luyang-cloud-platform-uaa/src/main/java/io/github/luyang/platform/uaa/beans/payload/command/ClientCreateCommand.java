package io.github.luyang.platform.uaa.beans.payload.command;

import java.util.Set;

public record ClientCreateCommand(
	String clientName,
	Set<String> redirectUris,
	int accessTokenValidity,
	int refreshTokenValidity,
	int authorizationCodeValidity,
	String description
) {
}
