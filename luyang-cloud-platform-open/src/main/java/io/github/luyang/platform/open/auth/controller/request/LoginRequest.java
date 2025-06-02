package io.github.luyang.platform.open.auth.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求体
 *
 * @author yang.lu
 */
@Getter
@Setter
public class LoginRequest {

	private String account;
	private String password;
	private String loginType;
}
