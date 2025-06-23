package io.github.luyang.platform.open.client.controller.response;

import java.util.List;

/**
 * 客户端响应 (Response)
 * 用于向客户端返回客户端信息
 *
 * @param clientId             客户端ID
 * @param clientName           应用名
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @param grantTypes           支持的授权类型
 * @param redirectUris         重定向URI
 * @param autoApprove          是否自动批准
 * @param description          应用描述
 * @author yang.lu
 */
public record ClientResponse(
	String clientId,
	String clientName,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	List<String> grantTypes,
	List<String> redirectUris,
	Boolean autoApprove,
	String description
) {
}
