package io.github.luyang.platform.open.token.repository.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import io.github.luyang.starter.security.SecurityUser;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_token", autoResultMap = true)
public class TokenDO extends BaseEntity<TokenDO, Long> {

	private String clientId;
	private String userId;
	@TableField(typeHandler = JacksonTypeHandler.class)
	private SecurityUser attachedInfo;
	private String accessToken;
	private String refreshToken;
	private LocalDateTime accessTokenExpiresTime;
	private LocalDateTime refreshTokenExpiresTime;
}
