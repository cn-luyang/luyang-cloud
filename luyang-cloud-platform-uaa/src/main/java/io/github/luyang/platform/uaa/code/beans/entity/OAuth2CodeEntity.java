package io.github.luyang.platform.uaa.code.beans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 授权码表 实体
 *
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "oauth2_code", autoResultMap = true)
public class OAuth2CodeEntity extends BaseEntity {

	/**
	 * 授权码
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String code;

	/**
	 * 客户端 ID
	 */
	private String clientId;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 申请的授权范围
	 */
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Set<String> scopes;

	/**
	 * 使用的回调地址
	 */
	private String redirectUri;

	/**
	 * OIDC Nonce参数
	 */
	private String nonce;

	/**
	 * PKCE 验证码
	 */
	private String codeChallenge;

	/**
	 * PKCE 计算方式
	 */
	private String codeChallengeMethod;

	/**
	 * 颁发时间
	 */
	private LocalDateTime issuedTime;

	/**
	 * 过期时间
	 */
	private LocalDateTime expiresTime;

	/**
	 * 是否已使用: {[1:已使用:true] [0:未使用:false]}
	 */
	private Boolean used;

	/**
	 * 使用时间
	 */
	private LocalDateTime usedTime;
}
