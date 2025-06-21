package io.github.luyang.platform.uac.user.service;

import io.github.luyang.platform.uac.base.valueobject.Email;
import io.github.luyang.platform.uac.user.controller.request.CreateUserReq;
import io.github.luyang.platform.uac.user.controller.response.CreateUserRes;
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
	 * @param createUserReq 创建用户请求体
	 * @return 创建用户请求体
	 * @author yang.lu
	 */
	public CreateUserRes createUser(CreateUserReq createUserReq) {

		// 构建并校验 Email 唯一性
		Email email = Email.build(createUserReq.getEmail());
		email.assertUnique(() -> userRepository.unique(email));

		// 将 Request 请求转换为实体并保存至数据库
		UserDO userDO = userConvert.toEntity(createUserReq);
		userRepository.save(userDO);

		return CreateUserRes.builder().userId(userDO.getUserId()).build();
	}

	public UserDO getUser(Email email) {
		return userRepository.find(email);
	}
}
