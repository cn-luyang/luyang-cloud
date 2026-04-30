package io.github.luyang.platform.uaa.beans.payload.vo;

public record CreateClientVO(
	String clientId,
	String clientSecret
) {

	public static CreateClientVO build(String clientId, String clientSecret) {
		return new CreateClientVO(clientId, clientSecret);
	}
}
