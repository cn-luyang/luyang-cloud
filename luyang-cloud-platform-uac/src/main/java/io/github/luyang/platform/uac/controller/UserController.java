package io.github.luyang.platform.uac.controller;

import io.github.luyang.platform.uac.beans.dto.CreateUserDTO;
import io.github.luyang.platform.uac.service.UserService;
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
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping
	public Result<?> create(@Valid @RequestBody CreateUserDTO createUserDTO) {
		return Result.success();
	}
}
