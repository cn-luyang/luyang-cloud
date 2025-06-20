package io.github.luyang.api.uac.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class VerifyAccountParam implements Serializable {

	@Serial
	private static final long serialVersionUID = -3540322939200130083L;

	/** 用户名/邮箱/手机号 */
	private String account;

	/** 密码或其他凭证 */
	private String secret;
}
