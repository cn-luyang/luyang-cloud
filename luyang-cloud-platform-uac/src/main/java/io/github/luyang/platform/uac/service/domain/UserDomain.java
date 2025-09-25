package io.github.luyang.platform.uac.service.domain;

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
