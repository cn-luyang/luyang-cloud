package io.github.luyang.platform.open.base.valueobject;

import io.github.luyang.platform.open.base.enums.error.ClientError;

import java.util.function.Supplier;

/**
 * 客户端ID
 *
 * @author yang.lu
 */
public record ClientId(String value) {

	public static ClientId build(String clientId) {
		return new ClientId(clientId);
	}

	public void checkEmpty(Supplier<Boolean> conditionSupplier) {
		ClientError.INVALID_CLIENT.isFalse(conditionSupplier.get());
	}
}
