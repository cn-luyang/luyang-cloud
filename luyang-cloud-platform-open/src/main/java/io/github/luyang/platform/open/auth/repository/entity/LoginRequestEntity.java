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
@TableName(value = "t_login_request", autoResultMap = true)
public class LoginRequestEntity extends BaseEntity<LoginRequestEntity, Long> {

	private Long authorizeRequestId;
	private String userId;
	private LocalDateTime expireTime;
}
