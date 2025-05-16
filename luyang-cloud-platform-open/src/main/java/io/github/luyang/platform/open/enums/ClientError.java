package io.github.luyang.platform.open.enums;

import io.github.luyang.starter.base.error.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClientError implements ExceptionAssert<String> {

	CLIENT_INVALID("client_invalid", "无效客户端"),
	CLIENT_NAME_EXISTS("client_name_exists", "客户端名称已存在"),

	TOKEN_VALIDITY_INVALID("token_validity_invalid", "无效的Access Token有效期");

	private final String code;
	private final String message;
}
