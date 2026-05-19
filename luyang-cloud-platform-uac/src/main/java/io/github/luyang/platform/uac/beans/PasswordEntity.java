package io.github.luyang.platform.uac.beans;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_password", autoResultMap = true)
public class PasswordEntity {

	@TableId
	private Long id;
	private String accountId;
	private String passwordHash;
	private LocalDateTime expireTime;
}
