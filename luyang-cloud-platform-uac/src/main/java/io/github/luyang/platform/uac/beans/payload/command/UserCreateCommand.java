package io.github.luyang.platform.uac.beans.payload.command;

/**
 * 用户创建请求参数
 *
 * @param cnName 中文名
 * @param phone  手机号
 * @param email  邮箱号
 * @author yang.lu
 */
public record UserCreateCommand(
	String cnName,
	String phone,
	String email
) {
}
