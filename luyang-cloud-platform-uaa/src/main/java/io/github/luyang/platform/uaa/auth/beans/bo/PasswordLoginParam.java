package io.github.luyang.platform.uaa.auth.beans.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * 密码登录参数
 *
 * @author yang.lu
 */
@Getter
@Setter
public class PasswordLoginParam extends LoginParam {

	private String account;
	private String password;
}
