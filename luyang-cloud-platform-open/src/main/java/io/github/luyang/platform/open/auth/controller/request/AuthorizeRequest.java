package io.github.luyang.platform.open.auth.controller.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class AuthorizeRequest {

	private String loginToken;
	private String responseType;
	private String clientId;
	private String redirectUri;
	private String scope;
	private String state;
	private String codeChallenge;
	private String codeChallengeMethod;
}
