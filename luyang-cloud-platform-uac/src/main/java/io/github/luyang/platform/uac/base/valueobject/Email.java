package io.github.luyang.platform.uac.base.valueobject;

import cn.hutool.core.lang.Validator;
import io.github.luyang.platform.uac.base.enums.error.UserError;

import java.util.function.Supplier;

public record Email(String value) {

	public static Email build(String userId) {
		return new Email(userId);
	}

	public void assertUnique(Supplier<Boolean> conditionSupplier) {
		assertFormat();
		UserError.EXISTS_EMAIL.isFalse(conditionSupplier.get());
	}

	public void assertFormat() {
		UserError.INVALID_EMAIL_FORMAT.isTrue(checkFormat());
	}

	public boolean checkFormat() {
		return Validator.isEmail(this.value);
	}
}
