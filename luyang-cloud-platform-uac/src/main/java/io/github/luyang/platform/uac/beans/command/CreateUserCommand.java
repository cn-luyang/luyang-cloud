package io.github.luyang.platform.uac.beans.command;

/**
 * 用户创建请求参数
 *
 * @param cnName 中文名
 * @param email  邮箱号
 * @author yang.lu
 */
public record CreateUserCommand(
	String cnName,
	String email
) {
}
