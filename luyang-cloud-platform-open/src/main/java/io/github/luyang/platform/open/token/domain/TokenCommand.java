package io.github.luyang.platform.open.token.domain;

import java.util.Map;

/**
 * Token命令对象 (Command)
 * 用于在 Service 层内部传递创建或更新操作的数据
 * 与外部请求DTO解耦，允许Service层内部数据结构的变化不影响Controller层
 *
 * @param clientId             客户端ID
 * @param userId               用户ID
 * @param attachedInfoMap      附带信息
 * @param accessToken          访问令牌
 * @param refreshToken         刷新令牌
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @author yang.lu
 */
public record TokenCommand(
	String clientId,
	String userId,
	Map<String, Object> attachedInfoMap,
	String accessToken,
	String refreshToken,
	Integer accessTokenValidity,
	Integer refreshTokenValidity
) {
	public TokenCommand(String clientId,
						Map<String, Object> attachedInfoMap,
						Integer accessTokenValidity,
						Integer refreshTokenValidity) {
		this(
			clientId, null, attachedInfoMap, null, null, accessTokenValidity,
			refreshTokenValidity
		);
	}
}
