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
	AUTH_LOGIN_FAILED("uaa:login_failed", "登录失败，请检查用户名或密码"),
	AUTH_LOGIN_LOCKED("auth:login_locked", "账号已被锁定，请稍后再试"),
	AUTH_LOGIN_EXPIRED("auth:login_expired", "登录已过期，请重新登录"),
	AUTH_TOKEN_INVALID("auth:token_invalid", "无效的Token"),
	AUTH_TOKEN_EXPIRED("auth:token_expired", "Token已过期"),
	AUTH_LOGIN_TYPE_UNSUPPORTED("auth:login_type_unsupported", "不支持的登录方式"),

	// 客户端相关
	CLIENT_NAME_EXISTS("uaa:client:exists", "客户端名已被占用"),
	;

	private final String code;
	private final String message;
}
