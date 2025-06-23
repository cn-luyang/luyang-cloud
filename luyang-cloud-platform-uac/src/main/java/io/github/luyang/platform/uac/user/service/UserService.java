package io.github.luyang.platform.uac.user.service;

import io.github.luyang.platform.uac.user.domain.UserCommand;
import io.github.luyang.platform.uac.user.domain.UserDomain;

/**
 * 用户业务服务接口
 *
 * @author yang.lu
 */
public interface UserService {

	/**
	 * 创建用户
	 *
	 * @param command 用户创建命令对象
	 * @return 用户业务对象
	 * @author yang.lu
	 */
	UserDomain create(UserCommand command);

	/**
	 * 通过邮箱号获取用户
	 *
	 * @param email 邮箱号
	 * @return 用户业务对象
	 * @author yang.lu
	 */
	UserDomain getUserByEmail(String email);
}
