package io.github.luyang.platform.open.service.impl;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.open.beans.command.UserTokenCreateCommand;
import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.domain.TokenDomain;
import io.github.luyang.platform.open.beans.request.LoginRequest;
import io.github.luyang.platform.open.constant.AuthConstant;
import io.github.luyang.platform.open.enums.LoginType;
import io.github.luyang.platform.open.enums.error.ClientError;
import io.github.luyang.platform.open.service.AuthService;
import io.github.luyang.platform.open.service.ClientService;
import io.github.luyang.platform.open.service.TokenService;
import io.github.luyang.platform.open.service.auth.AuthenticatorContext;
import io.github.luyang.platform.open.service.auth.AuthenticatorHandler;
import io.github.luyang.starter.base.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

/**
 * 认证业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final TokenService tokenService;
	private final ClientService clientService;
	private final HttpServletResponse httpServletResponse;

	@Override
	@SneakyThrows
	public void login(LoginRequest loginRequest) {

		// 获取客户端信息
		ClientDomain clientDomain = clientService.get(loginRequest.clientId());
		ClientError.NOT_FOUND_CLIENT.notNull(clientDomain);

		// 校验回调地址
		boolean validRedirectUri = clientDomain.isValidRedirectUri(loginRequest.redirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		// 获取认证处理器
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginRequest.loginType());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);

		// 执行认证逻辑
		authenticatorHandler.authenticate(loginRequest);

		// 构建用户token创建命名对象
		UserTokenCreateCommand userTokenCreateCommand = new UserTokenCreateCommand(
			clientDomain.clientId(),
			IdUtil.randomUUID(),
			new HashMap<>(),
			clientDomain.accessTokenValidity(),
			clientDomain.refreshTokenValidity()
		);

		// 创建用户 Token
		TokenDomain tokenDomain = tokenService.createUserToken(userTokenCreateCommand);

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
