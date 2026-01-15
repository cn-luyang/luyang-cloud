package io.github.luyang.platform.uaa.code.beans.bo;

public record AuthorizationCodeCreateResult(
	String code
) {

	public static AuthorizationCodeCreateResult build(String code) {
		return new AuthorizationCodeCreateResult(code);
	}
}
