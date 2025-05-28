package io.github.luyang.platform.uac.user.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 创建用户响应体
 *
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class CreateUserResponse {

	private String userId;
}
