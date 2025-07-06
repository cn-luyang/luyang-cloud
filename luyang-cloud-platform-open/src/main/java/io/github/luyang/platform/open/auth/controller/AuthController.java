package io.github.luyang.platform.open.auth.controller;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.service.AuthService;
import io.github.luyang.starter.base.api.Result;
import io.github.luyang.starter.security.annotation.AnonymousAccess;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public void login(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
		authService.login(loginRequest, httpServletResponse);
	}

	@AnonymousAccess
	@PostMapping("/introspect")
	public Result<Map<String, Object>> introspectToken(@RequestParam("accessToken") String accessToken) {
		return Result.success(authService.introspectToken(accessToken));
	}
}
