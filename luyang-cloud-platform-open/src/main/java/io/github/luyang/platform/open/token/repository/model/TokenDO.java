package io.github.luyang.platform.open.token.repository.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

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
	private Map<String, Object> attachedInfo;
	private String accessToken;
	private String refreshToken;
	private LocalDateTime accessTokenExpiresTime;
	private LocalDateTime refreshTokenExpiresTime;

	public void putAttachedInfo(String key, Object value) {
		this.attachedInfo.put(key, value);
	}
}
