package io.github.luyang.platform.uaa.auth.beans.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class LoginParam {

	/** URLEncode编码，登录成功的重定向地址 */
	private String target;

	/** 登录方式 */
	private String loginMethod;
}
