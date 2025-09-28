package io.github.luyang.platform.uaa.auth.beans.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class LoginParam {

	private String clientId;
	private String redirectUri;
	private String loginMethod;
}
