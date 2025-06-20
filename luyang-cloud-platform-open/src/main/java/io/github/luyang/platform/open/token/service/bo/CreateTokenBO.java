package io.github.luyang.platform.open.token.service.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class CreateTokenBO {

	private String clientId;
	private String userId;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
}
