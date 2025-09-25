package io.github.luyang.platform.uac.controller;

import io.github.luyang.platform.uac.controller.request.UserCreateReq;
import io.github.luyang.platform.uac.service.UserService;
import io.github.luyang.starter.base.common.model.Result;
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
	public Result<String> create(@RequestBody UserCreateReq userCreateReq) {
		String userId = userService.create(userCreateReq);
		return Result.success(userId);
	}
}
