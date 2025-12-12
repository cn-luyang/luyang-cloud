package io.github.luyang.platform.uaa.code.beans.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 授权码表 实体
 *
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "oauth2_code", autoResultMap = true)
public class OAuth2CodeEntity extends BaseEntity {

	@TableId
	private String code;
	private String clientId;
	private String userId;
	private String scope;
	private String redirectUri;
	private String nonce;
	private String codeChallenge;
	private String codeChallengeMethod;
	private LocalDateTime authenticatedAt;
	private LocalDateTime expiresAt;
	private Boolean consumed;
	private LocalDateTime consumedAt;
}
