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

	private String authorizeRequestId;

	/** 用户名/邮箱/手机号 */
	private String account;

	/** 密码或其他凭证 */
	private String secret;

	/** 认证类型(密码/短信等) */
	private String loginType;
}
