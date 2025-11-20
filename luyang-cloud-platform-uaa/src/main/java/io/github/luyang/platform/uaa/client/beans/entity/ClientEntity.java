package io.github.luyang.platform.uaa.client.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import io.github.luyang.starter.mybatis.support.type.StringListTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "client", autoResultMap = true)
public class ClientEntity extends BaseEntity {

	/** 客户端ID */
	@TableId
	private String clientId;

	/** 客户端名称 */
	private String clientName;

	/** 客户端密钥 */
	private String clientSecret;

	/** 授权类型 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> grantTypes;

	/** 授权回调地址 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> redirectUris;

	/** 授权范围 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private List<String> scopes;

	/** 客户端配置信息 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private ClientSettings clientSettings;

	/** 令牌配置信息 */
	@TableField(typeHandler = StringListTypeHandler.class)
	private TokenSettings tokenSettings;

	/** 描述 */
	private String description;

	/**
	 * 客户端配置信息
	 *
	 * @author yang.lu
	 */
	@Data
	public static class ClientSettings {
		private boolean requireProofKey = true;
		private boolean requireAuthorizationConsent = true;
	}

	/**
	 * 令牌配置信息
	 *
	 * @author yang.lu
	 */
	@Data
	public static class TokenSettings {
		private int accessTokenTtl = 3600;
		private int refreshTokenTtl = 30 * 24 * 3600;
		private boolean reuseRefreshTokens = true;
		private String idTokenSignatureAlgorithm = "RS256";
	}
}
