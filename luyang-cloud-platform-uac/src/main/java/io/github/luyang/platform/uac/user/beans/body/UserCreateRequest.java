package io.github.luyang.platform.uac.user.beans.body;

import io.github.luyang.platform.uac._common.enums.db.Gender;
import io.github.luyang.starter.base.validation.InEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 创建用户请求
 *
 * @param zhName   中文名
 * @param enName   英文名
 * @param email    邮箱号
 * @param gender   性别
 * @author yang.lu
 */
public record UserCreateRequest(

	@NotBlank(message = "中文名不能为空")
	@Size(max = 64, message = "中文名长度不能超过{max}个字符")
	String zhName,

	@Size(max = 64, message = "英文名长度不能超过{max}个字符")
	String enName,

	@NotBlank(message = "邮箱不能为空")
	@Email(message = "邮箱格式不正确")
	@Size(max = 32, message = "邮箱长度不能超过{max}个字符")
	String email,

	@InEnum(value = Gender.class, message = "性别值不在允许范围内")
	String gender
) {
}
