package io.github.luyang.api.uac.param;

import java.io.Serial;
import java.io.Serializable;

/**
 * 账号认证请求
 *
 * @param account 邮箱/手机号
 * @param secret  密码或其他凭证
 * @author yang.lu
 */
public record AccountAuthParam(
	String account,
	String secret
) implements Serializable {
	@Serial
	private static final long serialVersionUID = -3540322939200130083L;
}
