package io.github.luyang.platform.uaa.client.beans.body;

/**
 * 客户端创建响应
 *
 * @author yang.lu
 */
public record OAuth2ClientCreateResponse(String clientId) {

	public static OAuth2ClientCreateResponse build(String clientId) {
		return new OAuth2ClientCreateResponse(clientId);
	}
}
