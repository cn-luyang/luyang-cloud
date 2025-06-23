package io.github.luyang.platform.open.token.domain;

import cn.hutool.core.map.MapUtil;

import java.util.Map;
import java.util.Optional;

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
	public static TokenCommand buildCreateUserTokenParam(String clientId,
														 Map<String, Object> attachedInfoMap,
														 Integer accessTokenValidity,
														 Integer refreshTokenValidity) {

		String userId = null;
		if (MapUtil.isNotEmpty(attachedInfoMap)) {
			attachedInfoMap.put("clientId", clientId);
			userId = Optional.ofNullable(attachedInfoMap.get("userId"))
				.map(Object::toString)
				.orElse(null);
		}

		return new TokenCommand(
			clientId,
			userId,
			attachedInfoMap,
			null,
			null,
			accessTokenValidity,
			refreshTokenValidity
		);
	}
}
