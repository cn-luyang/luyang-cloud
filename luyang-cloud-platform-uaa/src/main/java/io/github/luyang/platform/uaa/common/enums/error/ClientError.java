package io.github.luyang.platform.uaa.common.enums.error;


import io.github.luyang.starter.base.common.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户端业务错误码枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ClientError implements ExceptionAssert<String> {

	INVALID_CLIENT("client:invalid_client", "无效客户端"),
	INVALID_REDIRECT_URI("client:invalid_redirect_uri", "无效回调地址"),
	EXISTS_CLIENT_NAME("client:exists_client_name", "客户端名称已存在");

	private final String code;
	private final String message;
}
