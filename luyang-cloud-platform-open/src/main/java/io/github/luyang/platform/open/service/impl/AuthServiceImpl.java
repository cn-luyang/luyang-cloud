package io.github.luyang.platform.open.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.open.beans.constant.AuthConstant;
import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.domain.TokenDomain;
import io.github.luyang.platform.open.beans.enums.LoginType;
import io.github.luyang.platform.open.beans.enums.error.ClientError;
import io.github.luyang.platform.open.beans.param.LoginParam;
import io.github.luyang.platform.open.beans.param.UserTokenCreateParam;
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

import java.util.Map;

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
	public void login(Map<String, Object> maps) {

		LoginParam loginParam = BeanUtil.toBean(maps, LoginParam.class);

		// 获取客户端信息
		ClientDomain clientDomain = clientService.get(loginParam.getClientId());
		ClientError.NOT_FOUND_CLIENT.notNull(clientDomain);

		// 校验回调地址
		boolean validRedirectUri = clientDomain.isValidRedirectUri(loginParam.getRedirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		// 获取认证处理器
		LoginType loginType = IBaseEnum.getByCode(LoginType.class, loginParam.getLoginType());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginType);

		// 执行认证逻辑
		AccountAuthResponse accountAuthResponse = authenticatorHandler.authenticate(maps);

		// 构建用户token创建命名对象
		UserTokenCreateParam userTokenCreateParam = new UserTokenCreateParam(
			clientDomain.clientId(),
			accountAuthResponse.userId(),
			BeanUtil.beanToMap(accountAuthResponse, MapUtil.newHashMap(), CopyOptions.create().setIgnoreProperties(AccountAuthResponse::userId)),
			clientDomain.accessTokenValidity(),
			clientDomain.refreshTokenValidity()
		);

		// 创建用户 Token
		TokenDomain tokenDomain = tokenService.createUserToken(userTokenCreateParam);

		// 构建重定向 URI
		String loginUri = UriComponentsBuilder
			.fromUriString(loginParam.getRedirectUri())
			.queryParam(AuthConstant.ACCESS_TOKEN, tokenDomain.accessToken())
			.queryParam(AuthConstant.REFRESH_TOKEN, tokenDomain.refreshToken())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(loginUri);
	}
}
