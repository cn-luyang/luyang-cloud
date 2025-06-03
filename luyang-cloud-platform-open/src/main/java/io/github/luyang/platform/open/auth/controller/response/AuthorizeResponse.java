package io.github.luyang.platform.open.auth.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class AuthorizeResponse {

	private String nextUri;
}
