package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.beans.request.LoginRequest;
import jakarta.validation.Valid;

/**
 * 认证业务服务接口
 *
 * @author yang.lu
 */
public interface AuthService {

	/**
	 * 登录
	 *
	 * @param loginRequest 登录请求
	 * @author yang.lu
	 */
	void login(@Valid LoginRequest loginRequest);
}
