package io.github.luyang.platform.uac.user.controller;

import io.github.luyang.platform.uac.base.converter.UserConvert;
import io.github.luyang.platform.uac.user.controller.request.UserCreateRequest;
import io.github.luyang.platform.uac.user.controller.response.UserResponse;
import io.github.luyang.platform.uac.user.domain.UserCommand;
import io.github.luyang.platform.uac.user.domain.UserDomain;
import io.github.luyang.platform.uac.user.service.UserService;
import io.github.luyang.starter.base.api.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 RESTful API 控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	private final UserConvert convert;

	@PostMapping
	public Result<UserResponse> create(@Valid @RequestBody UserCreateRequest createUserRequest) {
		UserCommand userCommand = this.convert.toCommand(createUserRequest);
		UserDomain userDomain = userService.create(userCommand);
		return Result.success(this.convert.toResponse(userDomain));
	}
}
