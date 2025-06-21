package io.github.luyang.platform.uac.user.controller.request;

import io.github.luyang.platform.uac.base.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 创建用户请求体
 *
 * @author yang.lu
 */
@Getter
@Setter
public class CreateUserReq {

	@NotBlank(message = "{validation.notBlank.zhName}")
	@Size(max = 64, message = "{validation.size.zhName}")
	private String zhName;

	@Size(max = 64, message = "{validation.size.enName}")
	private String enName;

	@NotBlank(message = "{validation.notBlank.email}")
	@Size(max = 32, message = "{validation.size.email}")
	private String email;

	private String password;

	@NotNull
	private Gender gender;
}
