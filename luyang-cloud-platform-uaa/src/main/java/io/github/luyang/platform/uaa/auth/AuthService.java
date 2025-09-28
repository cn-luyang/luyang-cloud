package io.github.luyang.platform.uaa.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa.auth.beans.bo.LoginParam;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorContext;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorHandler;
import io.github.luyang.platform.uaa.client.ClientService;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa.common.enums.error.ClientError;
import io.github.luyang.platform.uaa.token.TokenService;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateParam;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateResult;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private final TokenService tokenService;
	private final ClientService clientService;
	private final HttpServletResponse httpServletResponse;

	@SneakyThrows
	public void login(Map<String, Object> maps) {

		LoginParam loginParam = BeanUtil.toBean(maps, LoginParam.class);

		// 获取客户端信息
		ClientDomain clientDomain = clientService.getDomain(loginParam.getClientId());
		ClientError.NOT_FOUND_CLIENT.notNull(clientDomain);

		// 校验回调地址
		boolean validRedirectUri = clientDomain.isValidRedirectUri(loginParam.getRedirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		// 获取认证处理器
		LoginMethodEnum loginMethodEnum = IBaseEnum.getByCode(LoginMethodEnum.class, loginParam.getLoginMethod());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginMethodEnum);

		// 执行认证逻辑
		AccountAuthResponse accountAuthResponse = authenticatorHandler.authenticate(maps);

		// 构建用户token创建命名对象
		TokenCreateParam tokenCreateParam = new TokenCreateParam(
			clientDomain.clientId(),
			accountAuthResponse.userId(),
			BeanUtil.beanToMap(accountAuthResponse, MapUtil.newHashMap(), CopyOptions.create().setIgnoreProperties(AccountAuthResponse::userId)),
			clientDomain.accessTokenValidity(),
			clientDomain.refreshTokenValidity()
		);

		// 创建 Token
		TokenCreateResult tokenCreateResult = tokenService.create(tokenCreateParam);

		// 构建重定向 URI
		String loginUri = UriComponentsBuilder
			.fromUriString(loginParam.getRedirectUri())
			.queryParam("ACCESS_TOKEN", tokenCreateResult.accessToken())
			.queryParam("REFRESH_TOKEN", tokenCreateResult.refreshToken())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(loginUri);
	}
}
