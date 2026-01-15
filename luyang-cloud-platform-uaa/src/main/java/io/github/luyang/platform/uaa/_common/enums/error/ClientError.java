package io.github.luyang.platform.uaa._common.enums.error;


import io.github.luyang.starter.base.exception.ExceptionAssert;
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

	EXISTS_CLIENT_NAME("oauth2_client:exists_client_name", "客户端名称已存在"),
	CLIENT_SAVE_FAILED("oauth2_client:client_save_failed", "客户端信息入库保存失败"),
	CLIENT_NOT_FOUND("oauth2_client:client_not_found", "客户端不存在或已被删除"),
	INVALID_REDIRECT_URI("oauth2_client:invalid_redirect_uri", "无效的重定向 URI"),
	UNSUPPORTED_GRANT_TYPE("oauth2_client:unsupported_grant_type", "不支持的授权类型");

	private final String code;
	private final String message;
}
