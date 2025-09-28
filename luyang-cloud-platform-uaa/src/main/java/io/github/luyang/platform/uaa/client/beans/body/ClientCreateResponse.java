package io.github.luyang.platform.uaa.client.beans.body;

/**
 * @author yang.lu
 */
public record ClientCreateResponse(String clientId) {

	public static ClientCreateResponse build(String clientId) {
		return new ClientCreateResponse(clientId);
	}
}
