package io.github.luyang.api.uac.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class GetAuthUserParam {

	/** 用户名/邮箱/手机号 */
	private String account;

	/** 密码或其他凭证 */
	private String secret;

	/** 是否验证 密码或其他凭证 */
	private boolean verifySecret;
}
