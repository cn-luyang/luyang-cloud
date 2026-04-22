package io.github.luyang.platform.uaa.token.beans.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * 令牌实体
 *
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "OAuth2_token", autoResultMap = true)
public class OAuth2TokenEntity extends BaseEntity {

	/**
	 * 主键 ID
	 */
	@TableId
	private String id;

	/**
	 * 客户端 ID
	 */
	private String clientId;

	/**
	 * 用户 ID
	 */
	private String userId;

	/**
	 * 访问令牌
	 */
	private String accessToken;

	/**
	 * 刷新令牌
	 */
	private String refreshToken;

	/**
	 * OIDC ID Token
	 */
	private String idToken;

	/**
	 * Access Token 签发时间
	 */
	private LocalDateTime accessTokenIssuedAt;

	/**
	 * Access Token 过期时间
	 */
	private LocalDateTime accessTokenExpiresAt;

	/**
	 * Refresh Token 过期时间
	 */
	private LocalDateTime refreshTokenExpiresAt;

	/**
	 * 授权范围
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Set<String> scopes;

	/**
	 * 授权类型
	 */
	private String grantType;

	/**
	 * 附带信息
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Map<String, Object> attachedInfo;
}
