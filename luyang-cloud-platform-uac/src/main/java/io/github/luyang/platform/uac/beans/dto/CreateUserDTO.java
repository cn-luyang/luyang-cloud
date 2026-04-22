package io.github.luyang.platform.uac.beans.dto;

/**
 * @author yang.lu
 */
public record CreateUserDTO(
	String email,
	String encryptionId,
	String password,
	String confirmPassword
) {
}
