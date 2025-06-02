package io.github.luyang.platform.open.auth.service;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.controller.response.LoginResponse;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 认证服务类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	/**
	 * 登录
	 *
	 * @param loginRequest 登录请求体
	 * @return 登录响应体
	 * @author yang.lu
	 */
	public LoginResponse login(LoginRequest loginRequest) {

		// 获取对应的 GrantType 枚举实例
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginRequest.getLoginType());
		// 获取对应授权类型的认证处理器
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);
		// 执行认证逻辑
		authenticatorHandler.authenticate(loginRequest);

		return LoginResponse.builder().build();
	}
}
