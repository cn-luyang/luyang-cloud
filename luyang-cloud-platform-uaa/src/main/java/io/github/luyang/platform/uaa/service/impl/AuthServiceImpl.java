package io.github.luyang.platform.uaa.service.impl;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthCMD;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthResult;
import io.github.luyang.platform.uaa.beans.convert.AuthConvert;
import io.github.luyang.platform.uaa.beans.payload.LoginDTO;
import io.github.luyang.platform.uaa.common.enums.CacheKey;
import io.github.luyang.platform.uaa.service.AuthService;
import io.github.luyang.platform.uaa.service.TokenService;
import io.github.luyang.platform.uaa.strategy.authenticator.AuthenticatorContext;
import io.github.luyang.platform.uaa.strategy.authenticator.AuthenticatorHandler;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final TokenService tokenService;
	private final AuthConvert authConvert;

	private final RedissonHelper redissonHelper;

	private final HttpServletResponse httpServletResponse;
	private final HttpServletRequest httpServletRequest;

	@Override
	public void login(LoginDTO loginDTO) {

		// 获取认证器
		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginDTO.loginType());

		// 执行认证
		AccountAuthCMD accountAuthCMD = authConvert.buildAccountAuthCMD(loginDTO);
		AccountAuthResult accountAuthResult = authenticatorHandler.authenticate(accountAuthCMD);

		String tgc = IdUtil.fastSimpleUUID();

		String tgcCacheKey = CacheKey.AUTH_TGC.of(tgc);
		redissonHelper.setString(tgcCacheKey, accountAuthResult, CacheKey.AUTH_TGC.getTimeout());

		ResponseCookie cookie = ResponseCookie.from("__Host-SSO_TGC", tgc)
			.httpOnly(true)
			.secure(true)
			.path("/")
			.maxAge(Duration.ofHours(2))
			.sameSite("Lax")
			.build();
		httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

		// TODO 从缓存中获取/authorize接口参数信息，重定向到/authorize接口

		String redirectUrl = UriComponentsBuilder
			.fromPath("/auth/authorize")
			.queryParam("client_id", "xxxxxxxxxx")
			.queryParam("redirect_uri", "yyyyyyyyyyyy")
			.queryParam("response_type", "code")
			.queryParam("state", "sadasdasdasd")
			.build()
			.toUriString();

		httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
		httpServletResponse.setHeader(HttpHeaders.LOCATION, redirectUrl);
	}
}


