package io.github.luyang.platform.uac.service.impl;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uac.beans.UserDO;
import io.github.luyang.platform.uac.beans.convert.UserConvert;
import io.github.luyang.platform.uac.beans.payload.command.UserCreateCommand;
import io.github.luyang.platform.uac.common.enums.ErrorCode;
import io.github.luyang.platform.uac.repository.UserRepository;
import io.github.luyang.platform.uac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final UserConvert userConvert;

	@Override
	public String create(UserCreateCommand command) {
		boolean hasEmail = userRepository.emailUnique(command.email());
		ErrorCode.USER_EXISTS_EMAIL.isFalse(hasEmail);

		String password = command.password();
		String confirmPassword = command.confirmPassword();
		ErrorCode.USER_PASSWORD_MISMATCH.isTrue(StrUtil.equals(password, confirmPassword));

		UserDO userDO = userConvert.buildEntity(command);
		userRepository.save(userDO);

		return userDO.getUserId();
	}
}
