package io.github.luyang.platform.open.base.valueobject;

/**
 * @author yang.lu
 */
public record ClientName(String value) {

	public static ClientId build(String clientName) {
		return new ClientId(clientName);
	}
}
