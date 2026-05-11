package io.github.luyang.platform.uac.beans;

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
public class PasswordDO {

	private String id;
	private String accountId;
	private String passwordHash;
	private String salt;
	private LocalDateTime expire_time;
	private LocalDateTime last_change_time;
}
