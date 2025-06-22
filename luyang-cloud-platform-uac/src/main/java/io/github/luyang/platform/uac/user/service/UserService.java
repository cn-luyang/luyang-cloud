package io.github.luyang.platform.uac.user.service;

import io.github.luyang.platform.uac.base.enums.error.UserError;
import io.github.luyang.platform.uac.user.controller.request.CreateUserRequest;
import io.github.luyang.platform.uac.user.controller.response.CreateUserResponse;
import io.github.luyang.platform.uac.user.convert.UserConvert;
import io.github.luyang.platform.uac.user.repository.UserRepository;
import io.github.luyang.platform.uac.user.repository.entity.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户相关服务类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserConvert userConvert;
	private final UserRepository userRepository;

	/**
	 * 创建用户
	 *
	 * @param createUserRequest 创建用户请求体
	 * @return 创建用户请求体
	 * @author yang.lu
	 */
	public CreateUserResponse createUser(CreateUserRequest createUserRequest) {

		boolean hasEmail = userRepository.existsEmail(createUserRequest.getEmail());
		UserError.EXISTS_EMAIL.isFalse(hasEmail);

		// 转换为实体并保存至数据库
		UserDO userDO = userConvert.convertToUserDO(createUserRequest);
		userRepository.save(userDO);

		return CreateUserResponse.builder().userId(userDO.getUserId()).build();
	}
}
