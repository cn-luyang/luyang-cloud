package io.github.luyang.platform.open.token;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Token 实体
 *
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "token", autoResultMap = true)
public class TokenEntity extends BaseEntity {

	@TableId
	private String id;
	private String clientId;
	private String userId;
	@TableField(typeHandler = JacksonTypeHandler.class)
	private Map<String, Object> attachedInfo;
	private String accessToken;
	private String refreshToken;
	private LocalDateTime accessTokenExpiresTime;
	private LocalDateTime refreshTokenExpiresTime;
}
