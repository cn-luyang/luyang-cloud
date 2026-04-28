package io.github.luyang.platform.uaa.controller;

import cn.hutool.extra.servlet.JakartaServletUtil;
import io.github.luyang.platform.uaa.beans.payload.LoginDTO;
import io.github.luyang.platform.uaa.service.AuthService;
import io.github.luyang.starter.base.util.jackson.JsonUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public void login(@Valid @RequestBody LoginDTO loginDTO) {
		authService.login(loginDTO);
	}

	/**
	 * 授权端点
	 *
	 * @param clientId            客户端 ID
	 * @param redirectUri         授权成功后的回调地址
	 * @param responseType        响应类型，如"code"表示授权码流程
	 * @param scope               请求的权限范围
	 * @param state               防CSRF的随机字符串，由客户端生成
	 * @param nonce               防重放攻击的随机值，用于OIDC协议
	 * @param codeChallenge       PKCE码，code_verifier的哈希值
	 * @param codeChallengeMethod PKCE哈希算法
	 * @author yang.lu
	 */
	@GetMapping("/authorize")
	public void authorize(
		@RequestParam("client_id") String clientId,
		@RequestParam("redirect_uri") String redirectUri,
		@RequestParam(value = "response_type") String responseType,
//		@RequestParam(value = "scope", required = false) String scope,
		@RequestParam(value = "state", required = false) String state,
//		@RequestParam(value = "nonce", required = false) String nonce,
//		@RequestParam(value = "code_challenge") String codeChallenge,
//		@RequestParam(value = "code_challenge_method") String codeChallengeMethod,
		HttpServletRequest request
	) {

		Map<String, String[]> parameterMap = request.getParameterMap();
		System.out.println(JsonUtil.toJsonString(parameterMap));

		String requestURI = request.getRequestURI();
		System.out.println("requestURI:" + requestURI);

		System.out.println(clientId);
		Cookie sessionCookie = JakartaServletUtil.getCookie(request, "__Host-SSO_TGC");
		String s = Optional.ofNullable(sessionCookie)
			.map(Cookie::getValue)
			.get();
		System.out.println(s);

	}
}
