package io.github.luyang.platform.uac.user.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "user", autoResultMap = true)
public class UserEntity extends BaseEntity {

	/** 用户 ID */
	private String userId;

	/** 中文名 */
	private String zhName;

	/** 英文名 */
	private String enName;

	/** 邮箱 */
	private String email;

	/** 密码 */
	private String password;

	/** 性别 */
	private Integer gender;
}
