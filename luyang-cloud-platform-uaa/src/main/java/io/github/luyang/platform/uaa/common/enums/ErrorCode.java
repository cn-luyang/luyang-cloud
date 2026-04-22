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

	CLIENT_NAME_EXISTS("oauth2_client:name_exists", "客户端名称已存在"),
	CLIENT_SAVE_FAILED("oauth2_client:save_failed", "客户端信息保存失败"),
	CLIENT_NOT_FOUND("oauth2_client:not_found", "客户端不存在或已被删除"),
	CLIENT_REDIRECT_URI_INVALID("oauth2_client:invalid_redirect_uri", "无效的重定向 URI"),
	CLIENT_GRANT_TYPE_UNSUPPORTED("oauth2_client:unsupported_grant_type", "不支持的授权类型"),

	CODE_SAVE_FAILED("oauth2_code:save_failed", "授权码保存失败"),
	CODE_NOT_FOUND("oauth2_code:not_found", "授权码不存在或已被删除"),
	CODE_USED("oauth2_code:already_used", "授权码已被使用"),
	CODE_EXPIRED("oauth2_code:expired", "授权码已过期"),
	CODE_REDIRECT_URI_MISMATCH("oauth2_code:redirect_uri_mismatch", "重定向 URI 与申请时不匹配"),
	CODE_CLIENT_MISMATCH("oauth2_code:client_mismatch", "授权码归属客户端不匹配"),

	PKCE_METHOD_INVALID("oauth2_code:invalid_challenge_method", "PKCE 编码方式无效"),
	PKCE_METHOD_UNSUPPORTED("oauth2_code:unsupported_challenge_method", "不支持的 PKCE 编码方式"),
	PKCE_VERIFIER_FAILED("oauth2_code:verifier_mismatch", "PKCE 验证码校验失败"),

	LOGIN_METHOD_UNSUPPORTED("login:unsupported_method", "不支持的登录方式");

	private final String code;
	private final String message;
}
