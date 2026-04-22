package io.github.luyang.platform.uaa.auth.beans.body;

/**
 * @author yang.lu
 */
public record LoginResponse(
	String ticket
) {

	public static LoginResponse build(String ticket) {
		return new LoginResponse(ticket);
	}
}
