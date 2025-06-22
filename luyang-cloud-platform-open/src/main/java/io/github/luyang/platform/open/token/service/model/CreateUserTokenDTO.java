package io.github.luyang.platform.open.token.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class CreateUserTokenDTO {

	private String accessToken;
	private String refreshToken;
}
