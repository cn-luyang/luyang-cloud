package io.github.luyang.platform.open.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.open.auth.bo.LoginParam;
import io.github.luyang.platform.open.auth.strategy.AuthenticatorContext;
import io.github.luyang.platform.open.auth.strategy.AuthenticatorHandler;
import io.github.luyang.platform.open.client.ClientDomain;
import io.github.luyang.platform.open.client.ClientService;
import io.github.luyang.platform.open.common.constant.AuthConstant;
import io.github.luyang.platform.open.common.enums.LoginTypeEnum;
import io.github.luyang.platform.open.common.enums.error.ClientError;
import io.github.luyang.platform.open.token.TokenService;
import io.github.luyang.platform.open.token.bo.TokenCreateParam;
import io.github.luyang.platform.open.token.bo.TokenCreateResult;
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

	private final ClientService clientService;
	private final TokenService tokenService;
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
		LoginTypeEnum loginTypeEnum = IBaseEnum.getByCode(LoginTypeEnum.class, loginParam.getLoginType());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginTypeEnum);

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
		TokenCreateResult tokenCreateResult = tokenService.createToken(tokenCreateParam);

		// 构建重定向 URI
		String loginUri = UriComponentsBuilder
			.fromUriString(loginParam.getRedirectUri())
			.queryParam(AuthConstant.ACCESS_TOKEN, tokenCreateResult.accessToken())
			.queryParam(AuthConstant.REFRESH_TOKEN, tokenCreateResult.refreshToken())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(loginUri);
	}
}
