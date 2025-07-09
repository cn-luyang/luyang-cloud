package io.github.luyang.platform.open.base.enums.error;

import io.github.luyang.starter.base.error.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TokenError implements ExceptionAssert<String> {

	INVALID_ACCESS_TOKEN("invalid_access_token", "无效访问令牌"),
	EXPIRED_ACCESS_TOKEN("expired_access_token", "访问令牌已过期");

	private final String code;
	private final String message;
}
