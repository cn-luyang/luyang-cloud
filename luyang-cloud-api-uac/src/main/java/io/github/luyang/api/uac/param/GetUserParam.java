package io.github.luyang.api.uac.param;

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
public class GetUserParam implements Serializable {

	@Serial
	private static final long serialVersionUID = 5360031135975232631L;

	private String userId;
	private String email;
}
