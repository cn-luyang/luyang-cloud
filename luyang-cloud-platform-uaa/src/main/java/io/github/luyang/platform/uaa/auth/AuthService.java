package io.github.luyang.platform.uaa.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.validation.ValidationUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa._common.constant.OAuth2Constant;
import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa._common.enums.error.ClientError;
import io.github.luyang.platform.uaa._common.properties.OAuth2Properties;
import io.github.luyang.platform.uaa.auth.beans.bo.LoginParam;
import io.github.luyang.platform.uaa.auth.beans.body.AuthorizeRequest;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorContext;
import io.github.luyang.platform.uaa.auth.strategy.AuthenticatorHandler;
import io.github.luyang.platform.uaa.client.ClientService;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.code.OAuth2CodeService;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateResult;
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
import java.util.Optional;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private final ClientService clientService;
	private final OAuth2CodeService oAuth2CodeService;

	private final OAuth2Properties oAuth2Properties;

	private final RedissonHelper redissonHelper;

	private final HttpServletResponse httpServletResponse;
	private final HttpServletRequest httpServletRequest;

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
			OAuth2Constant.COOKIE_LOGIN_TICKET,
			ticket,
			Math.toIntExact(OAuth2Constant.LOGIN_TICKET_DURATION.getSeconds()));

		// Redis绑定登录凭证与用户ID，设置3分钟失效
		redissonHelper.setString(
			OAuth2Constant.REDIS_LOGIN_TICKET_KEY_PREFIX.concat(ticket),
			accountAuthResponse.userId(),
			OAuth2Constant.LOGIN_TICKET_DURATION);

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
		Cookie cookie = JakartaServletUtil.getCookie(httpServletRequest, OAuth2Constant.COOKIE_LOGIN_TICKET);

		// 过滤出Cookie值，从缓存中获取对应的UserId
		String userId = Optional.ofNullable(cookie)
			.map(Cookie::getValue)
			.filter(StrUtil::isNotBlank)
			.map(OAuth2Constant.REDIS_LOGIN_TICKET_KEY_PREFIX::concat)
			.map(redissonHelper::<String>getString)
			.orElse(null);

		// UserId 为空则跳转至前端登录界面
		if (StrUtil.isBlank(userId)) {
			// 当前请求完整的URL路径
			String currentRequestUrl = httpServletRequest.getRequestURL()
				.append("?")
				.append(httpServletRequest.getQueryString())
				.toString();

			// 前端登陆界面地址拼接target，用于登陆完成后重定向到/authorize接口与当前请求参数保持一致
			String loginUrl = UriComponentsBuilder.fromPath(oAuth2Properties.getLoginPageUrl())
				.queryParam(OAuth2Constant.PARAM_TARGET, URLEncoder.encode(currentRequestUrl, StandardCharsets.UTF_8))
				.build()
				.toUriString();

			httpServletResponse.sendRedirect(loginUrl);
			return;
		}

		// 下发Code
		OAuth2CodeCreateParam oAuth2CodeCreateParam = new OAuth2CodeCreateParam(
			authorizeRequest.clientId(),
			userId,
			authorizeRequest.scope(),
			authorizeRequest.redirectUri(),
			authorizeRequest.nonce(),
			authorizeRequest.codeChallenge(),
			authorizeRequest.codeChallengeMethod()
		);

		OAuth2CodeCreateResult oAuth2CodeCreateResult = oAuth2CodeService.create(oAuth2CodeCreateParam);
		String callbackUrl = UriComponentsBuilder.fromPath(oAuth2Properties.getCallbackUrl())
			.queryParam(OAuth2Constant.PARAM_CODE, oAuth2CodeCreateResult.code())
			.queryParam(OAuth2Constant.PARAM_STATE, authorizeRequest.state())
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(callbackUrl);
	}
}
