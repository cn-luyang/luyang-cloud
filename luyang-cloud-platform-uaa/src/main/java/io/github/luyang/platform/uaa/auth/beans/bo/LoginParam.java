package io.github.luyang.platform.uaa.auth.beans.bo;

import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.starter.base.validation.IsEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class LoginParam {

	/** URLEncode编码，登录成功的重定向地址 */
	@URL(message = "跳转地址格式不正确")
	private String target;

	/** 登录方式 */
	@IsEnum(value = LoginMethodEnum.class, message = "登录方式不正确")
	private String loginMethod;
}
