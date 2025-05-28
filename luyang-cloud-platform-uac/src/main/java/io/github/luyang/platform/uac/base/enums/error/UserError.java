package io.github.luyang.platform.uac.base.enums.error;

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

	USER_ID_DUPLICATE("user_id_duplicate", "重复的用户ID"),

	EMAIL_INVALID("email_invalid", "无效 Email"),
	EMAIL_EXISTS("email_exists", "Email 已存在"),
	;

	private final String code;
	private final String message;
}
