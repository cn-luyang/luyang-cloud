package io.github.luyang.platform.open.base.enums.error;

import io.github.luyang.starter.base.error.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户端错误枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClientError implements ExceptionAssert<String> {

	CLIENT_INVALID("client_invalid", "无效客户端"),
	INVALID_GRANT("invalid_grant", "无效授权方式"),
	CLIENT_NAME_EXISTS("client_name_exists", "客户端名称已存在"),

	TOKEN_VALIDITY_INVALID("token_validity_invalid", "无效的Access Token有效期");

	private final String code;
	private final String message;
}
