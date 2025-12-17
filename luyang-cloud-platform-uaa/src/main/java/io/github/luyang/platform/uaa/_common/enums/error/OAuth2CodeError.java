package io.github.luyang.platform.uaa._common.enums.error;


import io.github.luyang.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OAuth2 授权码错误枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OAuth2CodeError implements ExceptionAssert<String> {

	CODE_SAVE_FAILED("oauth2_code:code_save_failed", "授权码入库保存失败"),
	CODE_NOT_FOUND("oauth2_code:code_not_found", "授权码不存在或已被删除"),
	CODE_ALREADY_USED("oauth2_code:code_already_used", "授权码已被使用"),
	CODE_EXPIRED("oauth2_code:code_expired", "授权码已过期"),
	CODE_REDIRECT_URI_MISMATCH("oauth2_code:code_redirect_uri_mismatch", "授权码与重定向URI不匹配"),
	CODE_CLIENT_MISMATCH("oauth2_code:code_client_mismatch", "授权码与客户端不匹配"),

	INVALID_CODE_CHALLENGE_METHOD("oauth2_code:invalid_code_challenge_method", "PKCE 编码方式无效"),
	UNSUPPORTED_CODE_CHALLENGE_METHOD("unsupported_code_challenge_method", "不支持的 PKCE 编码方式"),
	CODE_VERIFIER_MISMATCH("oauth2_code:code_verifier_mismatch", "PKCE 验证码验证失败"),
	;

	private final String code;
	private final String message;
}
