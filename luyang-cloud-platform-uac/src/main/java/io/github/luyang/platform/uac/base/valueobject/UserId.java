package io.github.luyang.platform.uac.base.valueobject;

public record UserId(String value) {

	public static UserId build(String userId) {
		return new UserId(userId);
	}
}
