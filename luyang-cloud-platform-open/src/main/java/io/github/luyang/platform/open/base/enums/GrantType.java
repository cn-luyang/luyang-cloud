package io.github.luyang.platform.open.base.enums;

import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 授权类型相关枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GrantType implements IBaseEnum<String> {

	AUTHORIZATION_CODE("authorization_code", "授权码模式"),
	PASSWORD("password", "密码模式"),
	CLIENT_CREDENTIALS("client_credentials", "客户端模式"),
	REFRESH_TOKEN("refresh_token", "刷新令牌"),
	SMS("sms", "短信验证码");

	private final String code;
	private final String message;
}
