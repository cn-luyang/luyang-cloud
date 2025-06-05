package io.github.luyang.platform.open.auth.controller;

import io.github.luyang.platform.open.auth.controller.request.AuthorizeRequest;
import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.controller.response.AuthorizeResponse;
import io.github.luyang.platform.open.auth.controller.response.LoginResponse;
import io.github.luyang.platform.open.auth.service.AuthService;
import io.github.luyang.starter.base.api.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public Result<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return Result.success(authService.login(loginRequest));
	}

	@GetMapping("/authorize")
	public void authorize(@RequestParam("response_type") String response_type,
						  @RequestParam("client_id") String client_id,
						  @RequestParam("redirect_uri") String redirect_uri,
						  @RequestParam(value = "scope", required = false) String scope,
						  @RequestParam(value = "state", required = false) String state,
						  @RequestParam(value = "code_challenge", required = false) String code_challenge,
						  @RequestParam(value = "code_challenge_method", required = false) String code_challenge_method,
						  @RequestParam(value = "login_token", required = false) String login_token
	) {

		AuthorizeRequest authorizeRequest = new AuthorizeRequest();
		authorizeRequest.setLoginToken(login_token);
		authorizeRequest.setResponseType(response_type);
		authorizeRequest.setClientId(client_id);
		authorizeRequest.setRedirectUri(redirect_uri);
		authorizeRequest.setScope(scope);
		authorizeRequest.setState(state);
		authorizeRequest.setCodeChallenge(code_challenge);
		authorizeRequest.setCodeChallengeMethod(code_challenge_method);

		authService.authorize(authorizeRequest);
	}
}
