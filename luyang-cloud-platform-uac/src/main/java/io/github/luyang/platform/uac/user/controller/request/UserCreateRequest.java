package io.github.luyang.platform.uac.user.controller.request;

/**
 * 创建用户请求 (Request)
 *
 * @param zhName   中文名，非空，最大长度 64
 * @param enName   英文名，可选，最大长度 64
 * @param email    邮箱号，非空，最大长度 32
 * @param password 密码，非空
 * @param gender   性别，可选
 * @author yang.lu
 */
public record UserCreateRequest(
	String zhName,
	String enName,
	String email,
	String password,
	String gender
) {
}
