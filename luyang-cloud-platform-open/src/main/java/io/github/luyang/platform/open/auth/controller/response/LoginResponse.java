package io.github.luyang.platform.open.auth.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 登录响应体
 *
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class LoginResponse {

	/** 登录 Token */
	private String loginId;

	/** 登录 Token 到期时间 */
	private LocalDateTime expireTime;
}
