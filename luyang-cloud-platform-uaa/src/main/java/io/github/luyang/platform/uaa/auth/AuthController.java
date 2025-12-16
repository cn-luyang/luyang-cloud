package io.github.luyang.platform.uaa.auth;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa.auth.beans.body.AuthorizeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 认证相关控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	/**
	 * 登录
	 *
	 * @param maps 请求参数映射
	 * @author yang.lu
	 */
	@PostMapping("/login")
	public void login(@RequestBody Map<String, Object> maps) {
		authService.login(maps);
	}

	/**
	 * 授权端点
	 *
	 * @param clientId            客户端ID
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
		@RequestParam(value = "scope", required = false) String scope,
		@RequestParam(value = "state", required = false) String state,
		@RequestParam(value = "nonce", required = false) String nonce,
		@RequestParam(value = "code_challenge", required = false) String codeChallenge,
		@RequestParam(value = "code_challenge_method", required = false) String codeChallengeMethod
	) {

		Set<String> scopes = new HashSet<>(StrUtil.split(scope, StrUtil.SPACE));
		AuthorizeRequest authorizeRequest = new AuthorizeRequest(
			clientId, redirectUri, responseType, scopes, state, nonce, codeChallenge, codeChallengeMethod
		);

		authService.authorize(authorizeRequest);
	}
}
