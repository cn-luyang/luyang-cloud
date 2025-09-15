package io.github.luyang.platform.open.service;

import java.util.Map;

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
	void login(Map<String, Object> maps);
}
