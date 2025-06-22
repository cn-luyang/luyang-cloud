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
public class GetUserResult implements Serializable {

	@Serial
	private static final long serialVersionUID = -6979947839045616474L;

	private String userId;
	private String email;
	private String zhName;
	private String enName;
}
