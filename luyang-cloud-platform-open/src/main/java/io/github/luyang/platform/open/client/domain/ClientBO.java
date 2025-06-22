package io.github.luyang.platform.open.client.domain;

import cn.hutool.core.collection.CollUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 客户端业务领域对象 (Business Object - BO)
 * 封装客户端的核心业务属性和可能的业务方法
 *
 * @param id                   主键ID
 * @param clientId             客户端ID
 * @param clientName           应用名
 * @param clientSecret         客户端密钥 (密文)
 * @param accessTokenValidity  访问令牌有效期 (秒)
 * @param refreshTokenValidity 刷新令牌有效期 (秒)
 * @param grantTypes           支持的授权类型
 * @param redirectUris         重定向URI
 * @param autoApprove          是否自动批准
 * @param description          应用描述
 * @param createdBy            创建人
 * @param createdTime          创建时间
 * @param updatedBy            更新人
 * @param updatedTime          更新时间
 * @param deleted              是否删除
 * @author yang.lu
 */
public record ClientBO(
	Long id,
	String clientId,
	String clientName,
	String clientSecret,
	Integer accessTokenValidity,
	Integer refreshTokenValidity,
	List<String> grantTypes,
	List<String> redirectUris,
	Boolean autoApprove,
	String description,
	String createdBy,
	LocalDateTime createdTime,
	String updatedBy,
	LocalDateTime updatedTime,
	Boolean deleted
) {

	/**
	 * 判断客户端是否支持给定的授权类型
	 *
	 * @param grantType 要检查的授权类型
	 * @return 如果支持，则返回 true，否则返回 false
	 * @author yang.lu
	 */
	public boolean supportsGrantType(String grantType) {

		if (CollUtil.isEmpty(this.grantTypes)) {
			return false;
		}

		return this.grantTypes.stream()
			.map(String::trim)
			.toList()
			.contains(grantType);
	}

	/**
	 * 判断重定向URI是否有效或包含给定的URI
	 *
	 * @param uri 要检查的URI
	 * @return 如果重定向URI为空，或者包含给定的URI，则返回 true，否则返回 false
	 * @author yang.lu
	 */
	public boolean isValidRedirectUri(String uri) {

		if (CollUtil.isEmpty(this.redirectUris)) {
			// 如果 redirectUris 为空，通常表示不需要重定向URI，或者允许任何URI
			return true; // 根据具体业务规则调整
		}

		return this.redirectUris.stream()
			.map(String::trim)
			.toList()
			.contains(uri);
	}
}
