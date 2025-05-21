package io.github.luyang.platform.open.model;

import io.github.luyang.platform.open.enums.ClientError;

import java.util.function.Supplier;

/**
 * @author yang.lu
 */
public record ClientId(String value) {

	public static ClientId build(String clientId) {
		return new ClientId(clientId);
	}

	public void checkEmpty(Supplier<Boolean> conditionSupplier) {
		ClientError.CLIENT_INVALID.isFalse(conditionSupplier.get());
	}
}
