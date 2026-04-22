package io.github.luyang.platform.uaa.auth.beans.body;

/**
 * @author yang.lu
 */
public record LoginRequest(
	String loginMethod,
	String account,
	String credential
) {
}
