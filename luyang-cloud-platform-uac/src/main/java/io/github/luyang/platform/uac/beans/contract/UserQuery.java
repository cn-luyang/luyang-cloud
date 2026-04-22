package io.github.luyang.platform.uac.beans.contract;

import lombok.Builder;

/**
 * @author yang.lu
 */
@Builder
public class UserQuery {

	private String userId;
	private String email;
}
