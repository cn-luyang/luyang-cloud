package io.github.luyang.platform.uaa.client.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * OAuth2 客户端实体
 *
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "oauth2_client", autoResultMap = true)
public class OAuth2ClientEntity extends BaseEntity {

	/**
	 * 客户端 ID
	 */
	@TableId
	private String clientId;

	/**
	 * 客户端名称
	 */
	private String clientName;

	/**
	 * 客户端密钥
	 */
	private String clientSecret;

	/**
	 * 授权类型列表
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Set<String> grantTypes;

	/**
	 * 授权回调地址列表
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Set<String> redirectUris;

	/**
	 * 授权范围列表
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Set<String> scopes;

	/**
	 * 访问令牌有效期(秒)
	 */
	private Integer accessTokenValidity;

	/**
	 * 刷新令牌有效期(秒)
	 */
	private Integer refreshTokenValidity;

	/**
	 * 客户端描述
	 */
	private String description;
}
