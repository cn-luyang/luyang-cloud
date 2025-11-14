package io.github.luyang.platform.uac.user.beans.body;

/**
 * @author yang.lu
 */
public record UserCreateResponse(String userId) {

	public static UserCreateResponse build(String userId) {
		return new UserCreateResponse(userId);
	}
}
