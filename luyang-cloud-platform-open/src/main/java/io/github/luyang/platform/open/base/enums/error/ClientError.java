package io.github.luyang.platform.open.base.enums.error;

import io.github.luyang.starter.base.error.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户端相关错误枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClientError implements ExceptionAssert<String> {

	INVALID_CLIENT("invalid_client", "无效客户端"),
	INVALID_GRANT("invalid_grant", "无效授权方式"),
	EXISTS_CLIENT_NAME("exists_client_name", "客户端名称已存在"),

	INVALID_TOKEN_VALIDITY("invalid_token_validity", "无效的Access Token有效期");

	private final String code;
	private final String message;
}
