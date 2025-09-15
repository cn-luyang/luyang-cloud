package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.beans.domain.TokenDomain;
import io.github.luyang.platform.open.beans.param.UserTokenCreateParam;

/**
 * Token 业务服务接口
 *
 * @author yang.lu
 */
public interface TokenService {

	/**
	 * 创建用户 Token
	 *
	 * @param param 用户 Token 创建参数
	 * @return 创建成功的 Token DTO
	 * @author yang.lu
	 */
	TokenDomain createUserToken(UserTokenCreateParam param);
}
