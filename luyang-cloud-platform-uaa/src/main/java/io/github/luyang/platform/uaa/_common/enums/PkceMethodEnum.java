package io.github.luyang.platform.uaa._common.enums;

import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PKCE 计算方式
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum PkceMethodEnum implements IBaseEnum<String> {

	PLAIN("plain", "直接使用 code_verifier"),
	S256("S256", "SHA-256 + Base64URL 编码");

	private final String code;
	private final String message;
}
