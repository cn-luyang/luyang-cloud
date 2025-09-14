package io.github.luyang.platform.uac.enums.error;

import io.github.luyang.starter.base.error.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户相关错误枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserError implements ExceptionAssert<String> {

	DUPLICATE_USER_ID("duplicate_user_id", "重复的用户ID"),

	INVALID_EMAIL_FORMAT("invalid_email_format", "无效的邮箱格式"),
	INVALID_PASSWORD("invalid_password", "无效密码"),

	EXISTS_EMAIL("user:exists_email", "邮箱已存在"),

	NOT_FOUND_USER("not_found_user", "未找到用户信息"),
	;

	private final String code;
	private final String message;
}
