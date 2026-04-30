package io.github.luyang.platform.uaa.controller;

import io.github.luyang.platform.uaa.beans.payload.command.AuthorizeCommand;
import io.github.luyang.platform.uaa.beans.payload.command.LoginCommand;
import io.github.luyang.platform.uaa.beans.payload.command.TokenApplyCommand;
import io.github.luyang.platform.uaa.beans.payload.vo.TokenVO;
import io.github.luyang.platform.uaa.service.AuthService;
import io.github.luyang.starter.base.model.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
	public void login(@Valid @RequestBody LoginCommand command) {
		authService.login(command);
	}

	@GetMapping("/authorize")
	public void authorize(AuthorizeCommand command) {
		authService.authorize(command);
	}

	@PostMapping(value = "/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public Result<TokenVO> token(TokenApplyCommand command) {
		return Result.success(authService.applyToken(command));
	}
}
