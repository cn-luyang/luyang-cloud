package io.github.luyang.platform.open.auth.service;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.constant.AuthConstant;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.platform.open.token.service.TokenService;
import io.github.luyang.platform.open.token.service.model.CreateUserTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateUserTokenDTO;
import io.github.luyang.starter.base.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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
	 * 登录
	 *
	 * @param loginRequest 登录请求体
	 * @author yang.lu
	 */
	@SneakyThrows
	public void login(LoginRequest loginRequest, HttpServletResponse httpServletResponse) {

		// 客户端校验
		GetClientResponse getClientResponse = clientService.getClient(loginRequest.getClientId());
		ClientError.INVALID_CLIENT.notNull(getClientResponse);

		// 校验 redirect_uri
		String redirectUri = loginRequest.getRedirectUri();
		String redirectHost = UrlBuilder.of(redirectUri).getHost();
		boolean validRedirect = getClientResponse.getRedirectUris().stream()
			.map(uri -> UrlBuilder.of(uri).getHost())
			.anyMatch(host -> StrUtil.equals(host, redirectHost));
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirect);

		// 获取对应授权类型的认证处理器
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginRequest.getLoginType());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);

		// 执行认证逻辑
		String userId = authenticatorHandler.authenticate(loginRequest);

		// 创建Token
		CreateUserTokenBO createUserTokenBO = new CreateUserTokenBO();
		createUserTokenBO.setUserId(userId);
		createUserTokenBO.setClientId(getClientResponse.getClientId());
		createUserTokenBO.setAccessTokenValidity(getClientResponse.getAccessTokenValidity());
		createUserTokenBO.setRefreshTokenValidity(getClientResponse.getRefreshTokenValidity());
		CreateUserTokenDTO createUserTokenDTO = tokenService.createUserToken(createUserTokenBO);

		// 构建重定向 URI
		String loginUri = UriComponentsBuilder
			.fromUriString(loginRequest.getRedirectUri())
			.queryParam(AuthConstant.ACCESS_TOKEN, createUserTokenDTO.getAccessToken())
			.queryParam(AuthConstant.REFRESH_TOKEN, createUserTokenDTO.getRefreshToken())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(loginUri);
	}
}
