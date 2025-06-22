package io.github.luyang.platform.uac.base.valueobject;

public record Email(String value) {

	public static Email build(String userId) {
		return new Email(userId);
	}
}
