package io.github.luyang.platform.uaa.controller;

import io.github.luyang.platform.uaa.beans.payload.LoginDTO;
import io.github.luyang.platform.uaa.beans.payload.LoginVO;
import io.github.luyang.platform.uaa.service.AuthService;
import io.github.luyang.starter.base.model.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Result<LoginVO> login(@Valid @RequestBody LoginDTO loginDTO) {
		return Result.success(authService.login(loginDTO));
	}
}
