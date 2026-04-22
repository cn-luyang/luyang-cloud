package io.github.luyang.platform.uac.user.beans;

/**
 * @author yang.lu
 */
public record UserDomain(
	String userId,
	String zhName,
	String enName,
	String email
) {
}
