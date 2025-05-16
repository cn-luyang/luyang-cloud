package io.github.luyang.platform.open.model;

import io.github.luyang.platform.open.enums.ClientError;

import java.util.function.Supplier;

/**
 * @author yang.lu
 */
public record ClientName(String value) {

	public static ClientName build(String clientName) {
		return new ClientName(clientName);
	}

	public void checkUnique(Supplier<Boolean> conditionSupplier) {
		ClientError.CLIENT_NAME_EXISTS.isFalse(conditionSupplier.get());
	}
}
