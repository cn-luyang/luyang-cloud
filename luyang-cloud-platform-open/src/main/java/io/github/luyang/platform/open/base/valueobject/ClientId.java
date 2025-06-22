package io.github.luyang.platform.open.base.valueobject;

/**
 * @author yang.lu
 */
public record ClientId(String value) {

	public static ClientId build(String clientId) {
		return new ClientId(clientId);
	}
}
