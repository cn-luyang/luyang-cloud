package io.github.luyang.platform.uaa._common.enums;

import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PKCE (RFC 7636) 的 code_challenge_method 枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CodeChallengeMethodEnum implements IBaseEnum<String> {

	/**
	 * 直接使用 code_verifier，安全性低
	 */
	PLAIN("plain", "直接使用 code_verifier"),

	/**
	 * 使用 SHA-256 + Base64URL 编码，推荐方式
	 */
	S256("S256", "SHA-256 + Base64URL 编码");

	private final String code;
	private final String message;
}
