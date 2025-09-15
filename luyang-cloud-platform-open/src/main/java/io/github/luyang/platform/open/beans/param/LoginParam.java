package io.github.luyang.platform.open.beans.param;

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
	private String loginType;
}
