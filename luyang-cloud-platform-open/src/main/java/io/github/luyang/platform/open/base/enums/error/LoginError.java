package io.github.luyang.platform.open.base.enums.error;

import io.github.luyang.starter.base.error.ExceptionAssert;
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

	INVALID_LOGIN_TYPE("invalid_login_type", "无效登录类");

	private final String code;
	private final String message;
}
