package io.github.luyang.platform.uac.user.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体
 *
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_uac_user", autoResultMap = true)
public class UserEntity extends BaseEntity<UserEntity, String> {

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
