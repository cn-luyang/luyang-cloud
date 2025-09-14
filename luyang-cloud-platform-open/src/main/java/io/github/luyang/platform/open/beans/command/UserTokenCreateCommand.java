package io.github.luyang.platform.open.beans.command;

import java.util.Map;

/**
 * 用户Token创建命令对象
 *
 * @param clientId             客户端ID
 * @param userId               用户ID
 * @param attachedInfoMap      附带信息
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @author yang.lu
 */
public record UserTokenCreateCommand(
	String clientId,
	String userId,
	Map<String, Object> attachedInfoMap,
	Integer accessTokenValidity,
	Integer refreshTokenValidity
) {
}
