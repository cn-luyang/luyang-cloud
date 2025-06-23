package io.github.luyang.platform.open.token.service;

import io.github.luyang.platform.open.token.domain.TokenCommand;
import io.github.luyang.platform.open.token.domain.TokenDomain;

/**
 * Token 业务服务接口
 *
 * @author yang.lu
 */
public interface TokenService {

	/**
	 * @param command 用户 Token 创建命令对象
	 * @return Token 业务对象
	 * @author yang.lu
	 */
	TokenDomain createUserToken(TokenCommand command);
}
