package io.github.luyang.api.uac.request;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
public record AccountAuthRequest(
	String account,
	String credential
) implements Serializable {
	@Serial
	private static final long serialVersionUID = -3540322939200130083L;
}
