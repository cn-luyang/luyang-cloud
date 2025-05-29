package io.github.luyang.platform.open.auth.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
public class LoginResponse {

	private String loginToken;
	private LocalDateTime expiresTime;
}
