package io.github.luyang.platform.uaa.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa._common.enums.CodeChallengeMethodEnum;
import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa._common.enums.ResponseTypeEnum;
import io.github.luyang.platform.uaa._common.enums.error.ClientError;
import io.github.luyang.platform.uaa._common.enums.error.LoginError;
import io.github.luyang.platform.uaa.auth.beans.bo.LoginParam;
import io.github.luyang.platform.uaa.auth.beans.body.AuthorizeRequest;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorContext;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorHandler;
import io.github.luyang.platform.uaa.client.ClientService;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.token.TokenService;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateParam;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateResult;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

		String target = URLDecoder.decode(loginParam.getTarget(), StandardCharsets.UTF_8);
		LoginError.INVALID_TARGET_URI.notNull(target);

		// 获取认证处理器
		LoginMethodEnum loginMethodEnum = IBaseEnum.getByCode(LoginMethodEnum.class, loginParam.getLoginMethod());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginMethodEnum);

		// 执行认证逻辑
		AccountAuthResponse accountAuthResponse = authenticatorHandler.authenticate(maps);

		// TODO: 将认证信息放入Cookie供authorize接口校验是否以登录

		// 构建重定向地址
		String loginUri = UriComponentsBuilder
			.fromUriString(target)
			.build()
			.toUriString();

		// 认证成功，执行 302 跳转
		httpServletResponse.sendRedirect(loginUri);
	}

	public void authorize(AuthorizeRequest authorizeRequest) {

		// response_type 必须为 code
		boolean validResponseType = ResponseTypeEnum.CODE.getCode().equals(authorizeRequest.responseType());
		ClientError.RESPONSE_TYPE_MUST_BE_CODE.isTrue(validResponseType);

		// 校验客户端是否存在
		ClientDomain clientDomain = clientService.getDomain(authorizeRequest.clientId());
		ClientError.INVALID_CLIENT.notNull(clientDomain);

		// 校验 redirect_uri 是否在允许的回调地址中
		boolean validRedirectUri = clientDomain.isValidRedirectUri(authorizeRequest.redirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		// 验证scopes
		// boolean validScope = clientDomain.isValidScopes(authorizeRequest.scopes());
		// ClientError.INVALID_SCOPE.isTrue(validScope);

		// 校验 code_challenge_method 是否有效
		CodeChallengeMethodEnum codeChallengeMethodEnum = IBaseEnum.getByCode(
			CodeChallengeMethodEnum.class, authorizeRequest.codeChallengeMethod()
		);
		ClientError.INVALID_CODE_CHALLENGE_METHOD.notNull(codeChallengeMethodEnum);

		// 校验 code_challenge 是否存在
		boolean validCodeChallenge = StrUtil.isNotBlank(authorizeRequest.codeChallenge());
		ClientError.MISSING_CODE_CHALLENGE.isTrue(validCodeChallenge);

		/*
			基于Cookie检查用户是否已认证
				已经认证
					保存当前请求参数及code，下发code携带state
				未认证
					跳转到登录界面拼接当前请求参数
		 */
	}

	/*@SneakyThrows
	public void login(Map<String, Object> maps) {

		LoginParam loginParam = BeanUtil.toBean(maps, LoginParam.class);

		// 获取客户端信息
		ClientDomain clientDomain = clientService.getDomain(loginParam.getClientId());
		ClientError.INVALID_CLIENT.notNull(clientDomain);

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
	}*/
}
