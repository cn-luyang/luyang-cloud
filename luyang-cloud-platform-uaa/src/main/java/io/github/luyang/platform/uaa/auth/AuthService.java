package io.github.luyang.platform.uaa.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.validation.ValidationUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa._common.constant.OAuthConstant;
import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa._common.enums.error.ClientError;
import io.github.luyang.platform.uaa._common.properties.LoginProperties;
import io.github.luyang.platform.uaa.auth.beans.bo.LoginParam;
import io.github.luyang.platform.uaa.auth.beans.body.AuthorizeRequest;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorContext;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorHandler;
import io.github.luyang.platform.uaa.client.ClientService;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.code.OAuth2CodeService;
import io.github.luyang.platform.uaa.token.TokenService;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private final TokenService tokenService;
	private final ClientService clientService;
	private final OAuth2CodeService oAuth2CodeService;
	private final HttpServletResponse httpServletResponse;
	private final HttpServletRequest httpServletRequest;
	private final LoginProperties loginProperties;
	private final RedissonHelper redissonHelper;

	/**
	 * 登录
	 *
	 * @param maps 登录请求参数映射
	 * @author yang.lu
	 */
	@SneakyThrows
	public void login(Map<String, Object> maps) {

		// 参数转换
		LoginParam loginParam = BeanUtil.toBean(maps, LoginParam.class);

		// 校验参数
		ValidationUtil.validate(loginParam);

		// 获取认证处理器
		LoginMethodEnum loginMethodEnum = IBaseEnum.getByCode(LoginMethodEnum.class, loginParam.getLoginMethod());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginMethodEnum);

		// 执行认证逻辑
		AccountAuthResponse accountAuthResponse = authenticatorHandler.authenticate(maps);

		// 生成登录凭证
		String ticket = IdUtil.fastSimpleUUID();

		// 将登录凭证添加到Cookie中，设置3分钟失效
		JakartaServletUtil.addCookie(
			httpServletResponse,
			OAuthConstant.COOKIE_LOGIN_TICKET,
			ticket,
			Math.toIntExact(OAuthConstant.LOGIN_TICKET_DURATION.getSeconds()));

		// Redis绑定登录凭证与用户ID，设置3分钟失效
		redissonHelper.setString(
			OAuthConstant.REDIS_LOGIN_TICKET_KEY_PREFIX.concat(ticket),
			accountAuthResponse.userId(),
			OAuthConstant.LOGIN_TICKET_DURATION);

		// 认证成功，执行 302 跳转
		httpServletResponse.sendRedirect(
			UriComponentsBuilder.fromUriString(loginParam.getTarget())
				.build()
				.toUriString());
	}

	@SneakyThrows
	public void authorize(AuthorizeRequest authorizeRequest) {

		// 校验参数
		ValidationUtil.validate(authorizeRequest);

		// 获取客户端信息
		ClientDomain clientDomain = clientService.getDomain(authorizeRequest.clientId());
		ClientError.INVALID_CLIENT.notNull(clientDomain);

		// 校验 redirect_uri 是否在允许的回调地址中
		boolean validRedirectUri = clientDomain.isValidRedirectUrl(authorizeRequest.redirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		// 获取登录凭证Cookie
		Cookie cookie = JakartaServletUtil.getCookie(httpServletRequest, OAuthConstant.COOKIE_LOGIN_TICKET);

		// 凭证Cookie为空则跳转至前端登录界面
		if (null == cookie || StrUtil.isBlank(cookie.getValue())) {

			// 当前请求完整的URL路径
			String currentRequestUrl = httpServletRequest.getRequestURL()
				.append("?")
				.append(httpServletRequest.getQueryString())
				.toString();

			// 前端登陆界面地址拼接target，用于登陆完成后重定向到/authorize接口与当前请求参数保持一致
			String loginUrl = UriComponentsBuilder.fromPath(loginProperties.getPageUrl())
				.queryParam("target", URLEncoder.encode(currentRequestUrl, StandardCharsets.UTF_8))
				.build()
				.toUriString();

			httpServletResponse.sendRedirect(loginUrl);
			return;
		}

		// TODO：下发Code
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
