package io.github.luyang.platform.uaa.common.enums;

import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 授权类型
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GrantType implements IBaseEnum<String> {

	AUTHORIZATION_CODE("authorization_code ", "授权码模式"),
	CLIENT_CREDENTIALS("client_credentials ", "客户端模式"),
	REFRESH_TOKEN("refresh_token ", "刷新模式");

	private final String code;
	private final String message;
}
