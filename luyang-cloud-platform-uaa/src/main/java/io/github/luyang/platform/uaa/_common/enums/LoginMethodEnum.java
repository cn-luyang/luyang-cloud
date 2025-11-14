package io.github.luyang.platform.uaa._common.enums;

import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LoginMethodEnum implements IBaseEnum<String> {

	PASSWORD("password ", "密码登录"),
	SMS("sms ", "短信登录"),
	EMAIL("email ", "邮箱登录");
	private final String code;
	private final String message;
}
