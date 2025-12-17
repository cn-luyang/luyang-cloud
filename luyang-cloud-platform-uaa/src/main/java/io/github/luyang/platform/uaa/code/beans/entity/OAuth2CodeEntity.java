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

	@TableId(type = IdType.ASSIGN_UUID)
	private String code;
	private String clientId;
	private String userId;
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Set<String> scopes;
	private String redirectUri;
	private String nonce;
	private String codeChallenge;
	private String codeChallengeMethod;
	private LocalDateTime expiresTime;
	private Boolean used;
	private LocalDateTime usedTime;
}
