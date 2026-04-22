package io.github.luyang.platform.uaa.beans;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_token", autoResultMap = true)
public class TokenDO extends BaseEntity {

	/**
	 * 主键 ID
	 */
	@TableId
	private String id;

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
	 * Token 签发时间
	 */
	private LocalDateTime tokenIssuedTime;

	/**
	 * Access Token 过期时间
	 */
	private LocalDateTime accessTokenExpiresTime;

	/**
	 * Refresh Token 过期时间
	 */
	private LocalDateTime refreshTokenExpiresTime;
}
