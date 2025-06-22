package io.github.luyang.platform.open.token.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class CreateUserTokenBO {

	private String clientId;
	private String userId;
	private Integer accessTokenValidity;
	private Integer refreshTokenValidity;
}
