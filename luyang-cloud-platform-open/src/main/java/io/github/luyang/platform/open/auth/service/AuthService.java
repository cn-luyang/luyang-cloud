package io.github.luyang.platform.open.auth.service;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.controller.response.LoginResponse;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.enums.GrantType;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private final ClientService clientService;

	public LoginResponse login(LoginRequest loginRequest) {

		// 查找客户端并校验客户端是否存在
		GetClientResponse client = clientService.getClient(ClientId.build(loginRequest.getClientId()));
		ClientError.CLIENT_INVALID.notNull(client);

		// 检查客户端是否支持请求中的授权类型（grant_type），不支持则抛出 INVALID_GRANT 异常
		if (!client.getGrantTypes().contains(loginRequest.getGrantType())) {
			ClientError.INVALID_GRANT.exception();
		}

		// 获取对应的 GrantType 枚举实例
		GrantType grantType = IBaseEnum.getByCode(GrantType.class, loginRequest.getGrantType());
		// 获取对应授权类型的认证处理器
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(grantType);
		// 如果认证处理器不存在，抛出 INVALID_GRANT 异常
		ClientError.INVALID_GRANT.notNull(authenticatorHandler);
		// 执行认证逻辑
		authenticatorHandler.authenticate(loginRequest);

		return null;
	}
}
