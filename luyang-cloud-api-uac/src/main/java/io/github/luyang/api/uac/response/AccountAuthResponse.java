package io.github.luyang.api.uac.response;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
public record AccountAuthResponse(
	String userId
) implements Serializable {
	@Serial
	private static final long serialVersionUID = 3319455781457716061L;
}
