package io.github.luyang.platform.open.auth.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_auth_request", autoResultMap = true)
public class AuthRequestEntity extends BaseEntity<AuthRequestEntity, Long> {

	private String clientId;
	private String responseType;
	private String redirectUri;
	private String scope;
	private String state;
	private String codeChallenge;
	private String codeChallengeMethod;
	private LocalDateTime expireTime;
}
