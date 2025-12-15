package io.github.luyang.platform.uac.user;

import io.github.luyang.platform.uac.user.beans.body.UserCreateRequest;
import io.github.luyang.platform.uac.user.beans.body.UserCreateResponse;
import io.github.luyang.starter.base.model.Result;
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
	public Result<UserCreateResponse> create(@RequestBody UserCreateRequest userCreateRequest) {
		return Result.success(userService.create(userCreateRequest));
	}
}
