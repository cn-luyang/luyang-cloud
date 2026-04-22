package io.github.luyang.platform.uac.beans.payload;

/**
 * @author yang.lu
 */
public record CreateUserDTO(
	String cnName,
	String email,
	String password,
	String confirmPassword
) {
}
