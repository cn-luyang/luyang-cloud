package io.github.luyang.platform.uac.beans;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.platform.uac.common.enums.business.AccountStatus;
import io.github.luyang.platform.uac.common.enums.business.AccountType;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.lu
 */
@Getter
@Setter
@TableName(value = "t_account", autoResultMap = true)
public class AccountEntity extends BaseEntity {

	@TableId
	private Long id;
	private String userId;
	private String username;
	private AccountType accountType;
	private String passwordId;
	private AccountStatus status;
}
