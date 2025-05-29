package io.github.luyang.platform.open.auth.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class LoginRequest {

	private String clientId;
	private String account;
	private String password;
	private String grantType;
}
