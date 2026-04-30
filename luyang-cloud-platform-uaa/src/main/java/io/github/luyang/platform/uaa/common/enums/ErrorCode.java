package io.github.luyang.platform.uaa.common.enums;


import io.github.luyang.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode implements ExceptionAssert<String> {

	// 登录相关
	AUTH_LOGIN_TYPE_UNSUPPORTED("uaa:login:login_type_unsupported", "不支持的登录方式"),
	AUTH_REQ_PARAM_NOT_FOUND("uaa:login:authorize_request_param_key_not_found", "授权请求接口参数标识不存在或已过期"),

	// ==================== 登录认证相关 ====================
	AUTH_OAUTH_GRANT_TYPE_UNSUPPORTED("uaa:auth:grant_type_unsupported", "不支持的授权类型"),
	AUTH_AUTHORIZATION_CODE_NOT_FOUND("uaa:auth:code_not_found", "授权码不存在或已被删除"),
	AUTH_CLIENT_MISMATCH("uaa:auth:client_mismatch", "授权码归属客户端不匹配"),
	AUTH_REDIRECT_URI_MISMATCH("uaa:auth:redirect_uri_mismatch", "重定向 URI 与申请时不匹配"),
	AUTH_PKCE_VERIFIER_FAILED("uaa:auth:pkce_verifier_failed", "PKCE 验证码校验失败"),

	// ==================== 客户端相关 ====================
	CLIENT_NAME_EXISTS("uaa:client:exists", "客户端名已被占用"),
	CLIENT_NOT_FOUND("uaa:client:not_found", "客户端不存在或已被删除"),
	CLIENT_REDIRECT_URI_INVALID("uaa:client:invalid_redirect_uri", "无效的重定向 URI"),
	;

	private final String code;
	private final String message;
}
