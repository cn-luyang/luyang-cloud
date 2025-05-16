package io.github.luyang.platform.open.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@Builder
public class CreateClientVO {

	/** 客户端 ID */
	private String clientId;

	/** 客户端密钥明文，仅创建时返回一次，用户自行保管好 */
	private String clientSecretPlain;
}
