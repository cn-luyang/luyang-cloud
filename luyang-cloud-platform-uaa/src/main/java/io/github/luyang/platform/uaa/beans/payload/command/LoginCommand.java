package io.github.luyang.platform.uaa.beans.payload.command;

import io.github.luyang.platform.uaa.common.enums.LoginType;

import java.util.Map;

/**
 * 登录请求
 *
 * @param loginType       登录方式 {@link LoginType}
 * @param account         账号（用户名/手机号/邮箱）
 * @param credential      凭证（密码/验证码等）
 * @param authReqParamKey 授权接口请求参数缓存 Key
 * @author yang.lu
 */
public record LoginCommand(
	LoginType loginType,
	String account,
	String credential,
	String authReqParamKey
) {

	public Map<String, Object> toMap() {
		return Map.of(
			"account", account,
			"credential", credential
		);
	}
}
