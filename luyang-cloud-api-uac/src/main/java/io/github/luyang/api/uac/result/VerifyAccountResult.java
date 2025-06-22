package io.github.luyang.api.uac.result;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class VerifyAccountResult implements Serializable {

	@Serial
	private static final long serialVersionUID = 1059260229535356569L;

	private String userId;
}
