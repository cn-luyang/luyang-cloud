package io.github.luyang.platform.uaa.token.beans.bo;

import java.util.Map;

/**
 * 用户 Token 创建参数
 *
 * @param clientId             客户端ID
 * @param userId               用户ID
 * @param attachedInfoMap      附带信息
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @author yang.lu
 */
public record OAuth2TokenCreateParam(
	String clientId,
	String userId,
	Map<String, Object> attachedInfoMap,
	Integer accessTokenValidity,
	Integer refreshTokenValidity
) {
}
