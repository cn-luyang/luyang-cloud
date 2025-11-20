package io.github.luyang.platform.uaa._common.enums.error;


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
	INVALID_SCOPE("client:invalid_scope", "无效的 Scopes"),
	INVALID_CODE_CHALLENGE_METHOD("client:invalid_code_challenge_method", "无效的 code_challenge_method"),
	INVALID_RESPONSE_TYPE("client:invalid_response_type", "无效的 response_type"),

	EXISTS_CLIENT_NAME("client:exists_client_name", "客户端名称已存在"),


	MISSING_OPENID_SCOPE("client:missing_openid_scope", "OIDC 授权必须包含 openid Scope"),
	MISSING_CODE_CHALLENGE("client:missing_code_challenge", "缺少 code_challenge，必须使用 PKCE"),


	// ===== OAuth2 response_type 相关 =====
	RESPONSE_TYPE_MUST_BE_CODE("client:response_type_must_be_code", "response_type 必须为 code"),

	// ===== PKCE 相关 =====
	CODE_VERIFIER_REQUIRED("client:code_verifier_required", "缺少 code_verifier 参数"),
	INVALID_CODE_VERIFIER("client:invalid_code_verifier", "无效的 code_verifier"),

	// ===== Redirect URI 校验 =====
	REDIRECT_URI_NOT_MATCH("client:redirect_uri_not_match", "回调地址与注册信息不匹配"),

	// ===== Client Metadata 注册校验 =====
	INVALID_GRANT_TYPE("client:invalid_grant_type", "无效的授权模式"),
	UNSUPPORTED_GRANT_TYPE("client:unsupported_grant_type", "不支持的授权模式"),
	INVALID_CLIENT_SECRET("client:invalid_client_secret", "无效的客户端密钥"),
	CLIENT_SECRET_REQUIRED("client:client_secret_required", "缺少 client_secret 参数"),

	// ===== 客户端状态 =====
	CLIENT_DISABLED("client:client_disabled", "客户端已被禁用"),
	CLIENT_NOT_FOUND("client:client_not_found", "客户端不存在");

	private final String code;
	private final String message;
}
