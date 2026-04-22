package io.github.luyang.platform.uaa.beans.payload;

import io.github.luyang.platform.uaa.common.enums.LoginMethod;

/**
 * @author yang.lu
 */
public record LoginDTO(
	LoginMethod loginMethod,
	String account,
	String credential
) {
}
