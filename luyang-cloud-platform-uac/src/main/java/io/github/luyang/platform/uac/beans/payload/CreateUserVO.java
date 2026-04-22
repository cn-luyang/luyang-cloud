package io.github.luyang.platform.uac.beans.payload;

/**
 * @author yang.lu
 */
public record CreateUserVO(String userId) {

	public static CreateUserVO build(String userId) {
		return new CreateUserVO(userId);
	}
}
