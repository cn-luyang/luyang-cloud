package io.github.luyang.platform.uac.user.controller;

import io.github.luyang.platform.uac.user.controller.request.CreateUserReq;
import io.github.luyang.platform.uac.user.controller.response.CreateUserRes;
import io.github.luyang.platform.uac.user.service.UserService;
import io.github.luyang.starter.base.api.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping
	public Result<CreateUserRes> create(@Valid @RequestBody CreateUserReq createUserReq) {
		return Result.success(userService.createUser(createUserReq));
	}
}
