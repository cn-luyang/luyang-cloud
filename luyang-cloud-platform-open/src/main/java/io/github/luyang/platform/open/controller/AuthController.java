package io.github.luyang.platform.open.controller;

import io.github.luyang.platform.open.service.AuthService;
import io.github.luyang.starter.security.annotation.Anonymous;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

	@Anonymous
	@PostMapping("/login")
	public void login(@RequestBody Map<String, Object> maps) {
		authService.login(maps);
	}
}
