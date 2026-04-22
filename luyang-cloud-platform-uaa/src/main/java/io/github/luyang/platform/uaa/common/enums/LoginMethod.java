package io.github.luyang.platform.uaa.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录方式
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LoginMethod implements IBaseEnum<String> {

	PASSWORD("password", "密码登录"),
	SMS("sms", "短信登录"),
	EMAIL("email", "邮箱登录");

	private final String code;
	private final String message;

	@JsonCreator  // 反序列化时支持 code 码
	public static LoginMethod fromCode(String code) {
		if (code == null) return null;
		for (LoginMethod status : values()) {
			if (status.code.equals(code)) {
				return status;
			}
		}
		throw new IllegalArgumentException("未知的状态码: " + code);
	}
}
