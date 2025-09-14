package io.github.luyang.platform.uac.controller;

import io.github.luyang.platform.uac.beans.request.UserCreateRequest;
import io.github.luyang.platform.uac.service.UserService;
import io.github.luyang.starter.base.api.Result;
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
	public Result<String> create(@RequestBody UserCreateRequest request) {
		String userId = userService.create(request);
		return Result.success(userId);
	}
}
