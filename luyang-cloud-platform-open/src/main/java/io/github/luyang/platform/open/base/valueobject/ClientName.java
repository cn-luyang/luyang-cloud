package io.github.luyang.platform.open.base.valueobject;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.base.enums.error.ClientError;

import java.util.function.Supplier;

/**
 * 客户端名
 *
 * @author yang.lu
 */
public record ClientName(String value) {

	public static ClientName build(String clientName) {
		return new ClientName(clientName);
	}

	public void checkUnique(Supplier<Boolean> conditionSupplier) {
		ClientError.EXISTS_CLIENT_NAME.isFalse(conditionSupplier.get());
	}

	public void checkUniqueIfChanged(String clientName, Supplier<Boolean> conditionSupplier) {
		if (!StrUtil.equals(clientName, value)) {
			checkUnique(conditionSupplier);
		}
	}
}
