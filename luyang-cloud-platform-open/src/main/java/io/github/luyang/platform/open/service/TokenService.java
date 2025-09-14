package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.beans.command.UserTokenCreateCommand;
import io.github.luyang.platform.open.beans.domain.TokenDomain;

/**
 * @author yang.lu
 */
public interface TokenService {

	/**
	 * 创建用户 Token
	 *
	 * @param command 用户 Token 创建命令
	 * @return 新创建的用户 Token 领域对象
	 */
	TokenDomain createUserToken(UserTokenCreateCommand command);
}
