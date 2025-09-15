package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.beans.request.LoginReq;

/**
 * 认证业务服务接口
 *
 * @author yang.lu
 */
public interface AuthService {

	/**
	 * 用户登录并跳转至回调地址
	 *
	 * @param loginReq 登录请求对象
	 */
	void login(LoginReq loginReq);
}
