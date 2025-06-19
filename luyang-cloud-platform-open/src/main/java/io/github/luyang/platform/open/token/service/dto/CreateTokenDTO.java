package io.github.luyang.platform.open.token.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class CreateTokenDTO {

	private String accessToken;
	private String refreshToken;
}
