package io.github.luyang.platform.uaa.client.beans.body;

/**
 * 客户端创建响应
 *
 * @author yang.lu
 */
public record ClientCreateResponse(String clientId) {

	public static ClientCreateResponse build(String clientId) {
		return new ClientCreateResponse(clientId);
	}
}
