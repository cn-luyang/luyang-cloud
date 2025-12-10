package io.github.luyang.platform.uaa._common.enums.error;


import io.github.luyang.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录相关错误枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LoginError implements ExceptionAssert<String> {

	LOGIN_PARAM_INCOMPLETE("login:param_incomplete", "缺失登录参数"),

	INVALID_LOGIN_TYPE("login:invalid_login_type", "无效登录类"),
	INVALID_AUTHORIZE_REQUEST_ID("login:invalid_authorize_request_id", "无效授权请求标识"),
	INVALID_ACCOUNT_OR_PASSWORD("login:invalid_account_or_password", "无效账号或密码"),
	INVALID_TARGET_URI("login:invalid_target_uri", "无效的 target 地址，无法完成登录后跳转");


	private final String code;
	private final String message;
}
