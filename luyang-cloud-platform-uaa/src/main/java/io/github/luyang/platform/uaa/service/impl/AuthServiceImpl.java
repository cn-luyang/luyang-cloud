package io.github.luyang.platform.uaa.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.convert.AuthConvert;
import io.github.luyang.platform.uaa.beans.dto.TempTicketDTO;
import io.github.luyang.platform.uaa.beans.payload.command.AuthorizeCommand;
import io.github.luyang.platform.uaa.beans.payload.command.LoginCommand;
import io.github.luyang.platform.uaa.beans.payload.command.TokenApplyCommand;
import io.github.luyang.platform.uaa.beans.payload.vo.TokenVO;
import io.github.luyang.platform.uaa.common.constant.AuthConstant;
import io.github.luyang.platform.uaa.common.enums.CacheKey;
import io.github.luyang.platform.uaa.common.enums.ErrorCode;
import io.github.luyang.platform.uaa.common.enums.GrantType;
import io.github.luyang.platform.uaa.service.AuthService;
import io.github.luyang.platform.uaa.service.ClientService;
import io.github.luyang.platform.uaa.service.strategy.authenticator.AuthenticatorContext;
import io.github.luyang.platform.uaa.service.strategy.authenticator.AuthenticatorHandler;
import io.github.luyang.platform.uaa.service.strategy.granter.TokenGranterContext;
import io.github.luyang.platform.uaa.service.strategy.granter.TokenGranterHandler;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final ClientService clientService;

	private final AuthConvert authConvert;

	private final RedissonHelper redissonHelper;

	private final HttpServletResponse httpServletResponse;
	private final HttpServletRequest httpServletRequest;

	/**
	 * 统一登录入口
	 * TGC：存储在浏览器 Cookie 中的全局票据，用于证明用户已登录
	 * TGT：存储在服务端 Redis 中的用户会话信息，通过 TGC 关联查找
	 * ST：服务票据（授权码），一次性使用，用于换取最终 Token
	 *
	 * @param command 登录请求命令
	 * @author yang.lu
	 */
	@Override
	public void login(LoginCommand command) {

		// 获取授权接口请求参数
		Map<String, Object> authParam = redissonHelper.getString(CacheKey.AUTH_REQ_PARAMS.of(command.authReqParamKey()));
		ErrorCode.AUTH_REQ_PARAM_NOT_FOUND.notNull(authParam);

		// 获取认证器
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(command.loginType());
		// 执行认证
		String userId = authenticatorHandler.authenticate(command.toMap());

		// 生成并缓存 TGC
		String tgcValue = IdUtil.fastSimpleUUID();
		String tgcRedisKey = CacheKey.AUTH_SSO_TGC.of(tgcValue);
		Duration tgcTimeOut = CacheKey.AUTH_SSO_TGC.getTimeout();
		redissonHelper.setString(tgcRedisKey, userId, tgcTimeOut);

		// 将 TGC 写入 Cookie
		JakartaServletUtil.addCookie(httpServletResponse, AuthConstant.COOKIE_SSO_TGC, tgcValue, (int) tgcTimeOut.toSeconds());

		// 重定向到授权接口，继续完成授权流程
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(AuthConstant.AUTHORIZE_REQ_PATH);
		authParam.forEach(uriComponentsBuilder::queryParam);
		httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
		httpServletResponse.setHeader(HttpHeaders.LOCATION, uriComponentsBuilder.build().toUriString());
	}

	@Override
	@SneakyThrows
	public void authorize(AuthorizeCommand command) {

		// 获取客户端信息
		String clientId = command.clientId();
		ClientDomain clientDomain = clientService.getDomain(clientId);
		ErrorCode.CLIENT_NOT_FOUND.notNull(clientDomain);

		// 判断重定向 URI 是否合法
		String redirectUri = command.redirectUri();
		boolean validRedirectUri = clientDomain.isValidRedirectUri(redirectUri);
		ErrorCode.CLIENT_REDIRECT_URI_INVALID.isTrue(validRedirectUri);

		// 判断是否已登录
		Cookie tgcCookie = JakartaServletUtil.getCookie(httpServletRequest, AuthConstant.COOKIE_SSO_TGC);
		if (null != tgcCookie) {
			String tgcValue = tgcCookie.getValue();
			if (StrUtil.isNotBlank(tgcValue)) {
				// 通过TGC 获取用户信息
				String userId = redissonHelper.getString(CacheKey.AUTH_SSO_TGC.of(tgcValue));
				if (StrUtil.isNotBlank(userId)) {
					// 续期 TGC
					redissonHelper.expire(CacheKey.AUTH_SSO_TGC.of(tgcValue), CacheKey.AUTH_SSO_TGC.getTimeout());

					// 生成 ST 临时票据
					String stValue = UUID.fastUUID().toString();
					TempTicketDTO tempTicketDTO = authConvert.buildTempTicketDTO(userId, command);
					redissonHelper.setString(CacheKey.AUTH_SSO_ST.of(stValue), tempTicketDTO, CacheKey.AUTH_SSO_ST.getTimeout());

					// 构建回调 URL 并重定向
					String callbackUrl = UriComponentsBuilder.fromUriString(command.redirectUri())
						.queryParam(AuthConstant.PARAM_CODE, stValue)
						.queryParamIfPresent(AuthConstant.PARAM_STATE, Optional.ofNullable(command.state()))
						.build()
						.toUriString();
					httpServletResponse.sendRedirect(callbackUrl);
					return;
				}

				// TGC 存在但关联的用户信息为空（可能是会话已过期），则清除Cookie 使其重新登录
				JakartaServletUtil.addCookie(httpServletResponse, AuthConstant.COOKIE_SSO_TGC, null, 0);
			}
		}

		// 未登录，缓存当前授权请求参数，重定向至登录页面
		Map<String, Object> params = BeanUtil.beanToMap(command);
		String authReqParamKey = CacheKey.AUTH_REQ_PARAMS.of(IdUtil.fastSimpleUUID());
		redissonHelper.setString(authReqParamKey, params, CacheKey.AUTH_REQ_PARAMS.getTimeout());

		// 构建登录页面 URL
		String loginUrl = UriComponentsBuilder.fromUriString("")
			.queryParam(AuthConstant.AUTHORIZE_REQ_PARAMS_KEY, authReqParamKey)
			.encode(StandardCharsets.UTF_8)
			.build()
			.toUriString();
		httpServletResponse.sendRedirect(loginUrl);
	}

	@Override
	public TokenVO applyToken(TokenApplyCommand command) {

		GrantType grantType = IBaseEnum.getByCode(GrantType.class, command.grantType());
		TokenGranterHandler tokenGranterHandler = TokenGranterContext.getOAuth2GrantHandler(grantType);
		tokenGranterHandler.grant(command);
		return null;
	}
}


