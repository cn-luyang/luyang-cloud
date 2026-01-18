package io.github.luyang.platform.uaa.auth.beans.bo;

import io.github.luyang.platform.uaa._common.enums.dict.LoginMethodEnum;
import io.github.luyang.starter.base.validation.InEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

/**
 * 登录参数基类
 *
 * @author yang.lu
 */
@Getter
@Setter
public class LoginParam {

	/**
	 * 登录成功的重定向地址，URLEncode编码
	 */
	@URL(message = "重定向地址格式不正确")
	private String target;

	/**
	 * 登录方式
	 */
	@InEnum(value = LoginMethodEnum.class, message = "登录方式不正确")
	private String loginMethod;
}
