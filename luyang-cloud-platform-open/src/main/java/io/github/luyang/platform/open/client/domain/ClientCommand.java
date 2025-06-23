package io.github.luyang.platform.open.client.domain;

import cn.hutool.core.collection.ListUtil;

import java.util.List;

/**
 * 客户端命令对象 (Command)
 * 用于在 Service 层内部传递创建或更新操作的数据
 * 与外部请求DTO解耦，允许Service层内部数据结构的变化不影响Controller层
 *
 * @param id                   主键ID (更新时使用)
 * @param clientId             客户端ID
 * @param clientName           应用名
 * @param clientSecret         客户端密钥 (密文，创建时可选，更新时可能不传或传新密文)
 * @param clientSecretPlain    客户端密钥明文 (创建时使用)
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @param grantTypes           支持的授权类型
 * @param redirectUris         重定向URI
 * @param autoApprove          是否自动批准
 * @param description          应用描述
 * @author yang.lu
 */
public record ClientCommand(
	Long id,
	String clientId,
	String clientName,
	String clientSecret,
	String clientSecretPlain,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	List<String> grantTypes,
	List<String> redirectUris,
	Boolean autoApprove,
	String description
) {

	public static ClientCommand buildValidateClientParam(String clientId, String redirectUri) {
		return new ClientCommand(
			null, clientId, null, null, null,
			null, null, null, ListUtil.of(redirectUri), false, null
		);
	}
}
