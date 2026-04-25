package io.github.luyang.platform.uaa.beans.payload;

import io.github.luyang.platform.uaa.common.enums.LoginType;

/**
 * @author yang.lu
 */
public record LoginDTO(
	LoginType loginType,
	String account,
	String credential
) {
}
