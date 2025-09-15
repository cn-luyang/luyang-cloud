package io.github.luyang.platform.open.controller;

import io.github.luyang.platform.open.beans.request.LoginReq;
import io.github.luyang.platform.open.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@PostMapping("/login")
	public void login(@Valid @RequestBody LoginReq loginReq) {
		authService.login(loginReq);
	}
}
