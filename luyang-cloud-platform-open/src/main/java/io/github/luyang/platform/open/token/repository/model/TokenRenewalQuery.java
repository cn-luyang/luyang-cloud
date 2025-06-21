package io.github.luyang.platform.open.token.repository.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class TokenRenewalQuery {

	private String clientId;
	private String userId;
	private LocalDateTime nowTime;
}
