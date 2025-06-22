package io.github.luyang.platform.open.auth.service;

import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.auth.controller.request.LoginReq;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorContext;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import io.github.luyang.platform.open.base.constant.AuthConstant;
import io.github.luyang.platform.open.base.enums.LoginType;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.client.controller.response.GetClientRes;
import io.github.luyang.platform.open.client.service.ClientService;
import io.github.luyang.platform.open.token.service.TokenService;
import io.github.luyang.platform.open.token.service.model.CreateTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateTokenDTO;
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
	 * @param loginReq 登录请求体
	 * @author yang.lu
	 */
	@SneakyThrows
	public void login(LoginReq loginReq, HttpServletResponse httpServletResponse) {

		// 客户端校验
		GetClientRes getClientRes = clientService.getClient(loginReq.getClientId());
		ClientError.INVALID_CLIENT.notNull(getClientRes);

		// 校验 redirect_uri
		String redirectUri = loginReq.getRedirectUri();
		String redirectHost = UrlBuilder.of(redirectUri).getHost();
		boolean validRedirect = getClientRes.getRedirectUris().stream()
			.map(uri -> UrlBuilder.of(uri).getHost())
			.anyMatch(host -> StrUtil.equals(host, redirectHost));
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirect);

		// 获取对应授权类型的认证处理器
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginReq.getLoginType());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);

		// 执行认证逻辑
		String userId = authenticatorHandler.authenticate(loginReq);

		// 创建Token
		CreateTokenBO createTokenBO = new CreateTokenBO();
		createTokenBO.setUserId(userId);
		createTokenBO.setClientId(getClientRes.getClientId());
		createTokenBO.setAccessTokenValidity(getClientRes.getAccessTokenValidity());
		createTokenBO.setRefreshTokenValidity(getClientRes.getRefreshTokenValidity());
		CreateTokenDTO createTokenDTO = tokenService.createToken(createTokenBO);

		// 构建重定向 URI
		String loginUri = UriComponentsBuilder
			.fromUriString(loginReq.getRedirectUri())
			.queryParam(AuthConstant.ACCESS_TOKEN, createTokenDTO.getAccessToken())
			.queryParam(AuthConstant.REFRESH_TOKEN, createTokenDTO.getRefreshToken())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(loginUri);
	}
}
