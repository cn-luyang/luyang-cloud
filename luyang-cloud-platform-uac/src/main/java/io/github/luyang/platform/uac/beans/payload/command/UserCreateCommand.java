package io.github.luyang.platform.uac.beans.payload.command;

/**
 * @author yang.lu
 */
public record UserCreateCommand(
	String cnName,
	String email,
	String password,
	String confirmPassword
) {
}
