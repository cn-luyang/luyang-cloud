package io.github.luyang.platform.open.auth.service;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.constant.AuthConstant;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.client.domain.ClientCommand;
import io.github.luyang.platform.open.client.domain.ClientDomain;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.platform.open.token.domain.TokenCommand;
import io.github.luyang.platform.open.token.domain.TokenDomain;
import io.github.luyang.platform.open.token.service.TokenService;
import io.github.luyang.starter.base.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * 认证服务类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private final ClientService clientService;
	private final TokenService tokenService;

	/**
	 * 用户登录
	 * Token生成后重定向到指定URI
	 *
	 * @param loginRequest 登录请求体
	 * @author yang.lu
	 */
	@SneakyThrows
	public void login(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {

		ClientCommand command = ClientCommand.buildValidateClientParam(loginRequest.clientId(), loginRequest.redirectUri());
		ClientDomain clientDomain = clientService.validate(command);

		// 获取对应授权类型的认证处理器
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginRequest.loginType());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);

		// 执行认证逻辑
		Map<String, Object> accountAuthMap = authenticatorHandler.authenticate(loginRequest);

		// 创建Token
		TokenCommand tokenCommand = TokenCommand.buildCreateUserTokenParam(
			clientDomain.clientId(),
			accountAuthMap,
			clientDomain.accessTokenValidity(),
			clientDomain.refreshTokenValidity()
		);
		TokenDomain tokenDomain = tokenService.createUserToken(tokenCommand);

		// 构建重定向 URI
		String loginUri = UriComponentsBuilder
			.fromUriString(loginRequest.redirectUri())
			.queryParam(AuthConstant.ACCESS_TOKEN, tokenDomain.accessToken())
			.queryParam(AuthConstant.REFRESH_TOKEN, tokenDomain.refreshToken())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(loginUri);
	}
}
