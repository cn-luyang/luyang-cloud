package io.github.luyang.platform.open.base.valueobject;

import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.client.service.ClientService;

/**
 * 客户端ID
 *
 * @author yang.lu
 */
public record ClientId(String value) {

	public static ClientId build(String clientId) {
		return new ClientId(clientId);
	}

	public void assertExists(ClientService clientService) {
		ClientError.INVALID_CLIENT.isTrue(clientService.existClient(this));
	}
}
