package io.github.luyang.platform.uaa.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.extra.validation.ValidationUtil;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uaa._common.constant.OAuth2Constant;
import io.github.luyang.platform.uaa._common.enums.GrantTypeEnum;
import io.github.luyang.platform.uaa._common.enums.LoginMethodEnum;
import io.github.luyang.platform.uaa._common.enums.infra.ErrorCode;
import io.github.luyang.platform.uaa._common.enums.infra.RedisKey;
import io.github.luyang.platform.uaa._common.properties.OAuth2Properties;
import io.github.luyang.platform.uaa.auth.beans.AuthConvert;
import io.github.luyang.platform.uaa.auth.beans.bo.LoginParam;
import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenRequest;
import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenResponse;
import io.github.luyang.platform.uaa.auth.beans.body.AuthorizeRequest;
import io.github.luyang.platform.uaa.auth.beans.body.LoginResponse;
import io.github.luyang.platform.uaa.auth.strategy.authenticator.AuthenticatorContext;
import io.github.luyang.platform.uaa.auth.strategy.authenticator.AuthenticatorHandler;
import io.github.luyang.platform.uaa.auth.strategy.grant.OAuth2GrantContext;
import io.github.luyang.platform.uaa.auth.strategy.grant.OAuth2GrantHandler;
import io.github.luyang.platform.uaa.client.OAuth2ClientService;
import io.github.luyang.platform.uaa.client.beans.OAuth2ClientDomain;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private final OAuth2ClientService clientService;
	private final OAuth2CodeService codeService;
	private final AuthConvert authConvert;

	private final OAuth2Properties oAuth2Properties;

	private final RedissonHelper redissonHelper;

	private final HttpServletResponse httpServletResponse;
	private final HttpServletRequest httpServletRequest;

	@SneakyThrows
	public LoginResponse login(Map<String, Object> maps) {

		// 参数转换
		LoginParam loginParam = BeanUtil.toBean(maps, LoginParam.class);

		// 校验参数
		ValidationUtil.validate(loginParam);

		// 获取认证处理器
		LoginMethodEnum loginMethodEnum = IBaseEnum.getByCode(LoginMethodEnum.class, loginParam.getLoginMethod());
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginMethodEnum);

		// 执行认证逻辑
		AccountAuthResponse authResult = authenticatorHandler.authenticate(maps);

		// 生成登录凭证
		String ticket = IdUtil.fastSimpleUUID();
		redissonHelper.setString(RedisKey.LOGIN_TICKET.buildKey(ticket), authResult, RedisKey.LOGIN_TICKET.getTtl());

		return LoginResponse.build(ticket);
	}

	@SneakyThrows
	public void authorize(AuthorizeRequest authorizeRequest) {

		// 校验参数
		ValidationUtil.validate(authorizeRequest);

		// 获取客户端信息
		OAuth2ClientDomain clientDomain = clientService.getDomainByClientId(authorizeRequest.clientId());
		ErrorCode.CLIENT_NOT_FOUND.notNull(clientDomain);

		// 校验 redirect_uri 是否在允许的回调地址中
		boolean validRedirectUri = clientDomain.isValidRedirectUri(authorizeRequest.redirectUri());
		ErrorCode.CLIENT_REDIRECT_URI_INVALID.isTrue(validRedirectUri);

		String userId = null;

		// 优先查看是否已经登录过
		Cookie sessionCookie = JakartaServletUtil.getCookie(httpServletRequest, OAuth2Constant.COOKIE_SSO_SID);
		if (null != sessionCookie && StrUtil.isNotBlank(sessionCookie.getValue())) {
			String sessionKey = RedisKey.SSO_SID.buildKey(sessionCookie.getValue());
			userId = redissonHelper.getString(sessionKey);
		}

		// 如果没有有效会话，尝试通过 Login Ticket 建立新会话
		String loginTicket = authorizeRequest.loginTicket();
		if (StrUtil.isBlank(userId) && StrUtil.isNotBlank(loginTicket)) {
			String ticketKey = RedisKey.LOGIN_TICKET.buildKey(loginTicket);
			userId = redissonHelper.getString(ticketKey);
			if (StrUtil.isNotBlank(userId)) {
				// 删除临时登陆凭证
				redissonHelper.remove(ticketKey);
				// 创建新的 SSO 会话
				String sessionId = IdUtil.fastSimpleUUID();
				String sessionKey = RedisKey.SSO_SID.buildKey(sessionId);
				// Redis 缓存 SID 和 UserId 的映射
				redissonHelper.setString(sessionKey, userId, RedisKey.SSO_SID.getTtl());

				// 写 Session Cookie 到浏览器
				ResponseCookie cookie = ResponseCookie.from(OAuth2Constant.COOKIE_SSO_SID, sessionId)
					.httpOnly(true)
					.secure(true)
					.path("/")
					.maxAge(Duration.ofHours(2))
					.sameSite("Lax") // 允许从第三方站点跳转时携带 Cookie
					.build();
				httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
			}
		}

		// 若未获取到 UserId，说明用户未登录，重定向至登录页
		if (StrUtil.isBlank(userId)) {
			// 当前请求完整的 URL 路径
			String currentRequestUrl = httpServletRequest.getRequestURL()
				.append("?")
				.append(httpServletRequest.getQueryString())
				.toString();

			// 前端登陆界面地址拼接target，用于登陆完成后重定向到/authorize接口与当前请求参数保持一致
			String loginUrl = UriComponentsBuilder.fromUriString(oAuth2Properties.getLoginPageUrl())
				.queryParam(OAuth2Constant.PARAM_TARGET, currentRequestUrl)
				.replaceQueryParam(OAuth2Constant.PARAM_LOGIN_TICKET)
				.encode(StandardCharsets.UTF_8)
				.build()
				.toUriString();

			httpServletResponse.sendRedirect(loginUrl);
			return;
		}

		// 已获授权，生成 Authorization Code
		OAuth2CodeCreateParam codeParam = new OAuth2CodeCreateParam(
			authorizeRequest.clientId(),
			userId,
			authorizeRequest.scopes(),
			authorizeRequest.redirectUri(),
			authorizeRequest.nonce(),
			authorizeRequest.codeChallenge(),
			authorizeRequest.codeChallengeMethod(),
			LocalDateTime.now().plusMinutes(3)
		);

		OAuth2CodeCreateResult createResult = codeService.create(codeParam);

		// 回调客户端
		String callbackUrl = UriComponentsBuilder.fromUriString(authorizeRequest.redirectUri())
			.queryParam(OAuth2Constant.PARAM_CODE, createResult.code())
			.queryParamIfPresent(OAuth2Constant.PARAM_STATE, Optional.ofNullable(authorizeRequest.state()))
			.build()
			.toUriString();

		httpServletResponse.sendRedirect(callbackUrl);
	}

	public ApplyTokenResponse applyToken(ApplyTokenRequest applyTokenRequest) {

		// 校验参数
		ValidationUtil.validate(applyTokenRequest);

		GrantTypeEnum grantTypeEnum = IBaseEnum.getByCode(GrantTypeEnum.class, applyTokenRequest.grantType());
		OAuth2GrantHandler grantHandler = OAuth2GrantContext.getOAuth2GrantHandler(grantTypeEnum);

		return grantHandler.handle(applyTokenRequest);
	}
}
