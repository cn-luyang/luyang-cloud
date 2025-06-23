package io.github.luyang.platform.uac.user.domain;

/**
 * 用户命令对象 (Command)
 * 用于在 Service 层内部传递创建或更新操作的数据
 * 与外部请求DTO解耦，允许Service层内部数据结构的变化不影响Controller层
 *
 * @param userId   用户ID
 * @param zhName   中文名
 * @param enName   英文名
 * @param email    邮箱号
 * @param password 密码
 * @param gender   性别
 * @author yang.lu
 */
public record UserCommand(
	Long id,
	String userId,
	String zhName,
	String enName,
	String email,
	String password,
	String gender
) {
}
