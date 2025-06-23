package io.github.luyang.api.uac.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * 账号认证响应
 *
 * @param userId 用户ID
 * @param email  邮箱号
 * @param zhName 中文名
 * @param enName 英文名
 * @author yang.lu
 */
public record AccountAuthDTO(
	String userId,
	String email,
	String zhName,
	String enName
) implements Serializable {

	@Serial
	private static final long serialVersionUID = 3319455781457716061L;
}
