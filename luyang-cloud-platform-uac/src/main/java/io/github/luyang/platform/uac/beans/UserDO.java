package io.github.luyang.platform.uac.beans;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_user", autoResultMap = true)
public class UserDO extends BaseEntity {

	private String userId;
	private String cnName;
	private String email;
	private String password;
}
