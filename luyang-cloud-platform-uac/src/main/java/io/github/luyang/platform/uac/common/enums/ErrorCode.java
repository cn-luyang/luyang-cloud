package io.github.luyang.platform.uac.common.enums;


import io.github.luyang.starter.base.exception.ExceptionAssert;
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
public enum ErrorCode implements ExceptionAssert<String> {

	// --- 用户模块 (User) ---
	USER_NOT_FOUND("user:not_found", "用户不存在"),
	USER_EXISTS_EMAIL("user:exists_email", "邮箱已被占用"),
	USER_EXISTS_USERNAME("user:exists_username", "用户名已被占用"),
	USER_PASSWORD_ERROR("user:password_error", "密码错误"),
	USER_PASSWORD_MISMATCH("user:password_mismatch", "两次输入的密码不一致"),
	USER_FORMAT_EMAIL("user:format_email", "邮箱格式不正确"),
	USER_LOCKED("user:locked", "账号已被锁定"),

	// --- 部门模块 (Department) ---
	DEPT_NOT_FOUND("dept:not_found", "部门不存在"),
	;

	private final String code;
	private final String message;
}
