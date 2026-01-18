package io.github.luyang.platform.uaa.code.beans.bo;

public record OAuth2CodeCreateResult(
	String code
) {

	public static OAuth2CodeCreateResult build(String code) {
		return new OAuth2CodeCreateResult(code);
	}
}
