package io.github.luyang.api.uac.request;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
@Builder
public record AccountAuthRequest(
	String account,
	String credential
) implements Serializable {
	@Serial
	private static final long serialVersionUID = -3540322939200130083L;
}
