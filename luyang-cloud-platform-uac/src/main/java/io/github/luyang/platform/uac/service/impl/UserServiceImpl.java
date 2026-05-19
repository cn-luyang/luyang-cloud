package io.github.luyang.platform.uac.service.impl;

import io.github.luyang.platform.uac.beans.UserDO;
import io.github.luyang.platform.uac.beans.convert.UserConvert;
import io.github.luyang.platform.uac.beans.payload.command.UserCreateCommand;
import io.github.luyang.platform.uac.common.enums.ErrorCode;
import io.github.luyang.platform.uac.common.enums.business.AccountType;
import io.github.luyang.platform.uac.repository.UserRepository;
import io.github.luyang.platform.uac.service.AccountService;
import io.github.luyang.platform.uac.service.PasswordService;
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

	private final AccountService accountService;
	private final PasswordService passwordService;

	private final UserRepository userRepository;
	private final UserConvert userConvert;

	@Override
	public String create(UserCreateCommand command) {

		// 邮箱号唯一校验
		boolean hasEmail = accountService.checkAccountUnique(command.email(), AccountType.EMAIL);
		ErrorCode.USER_EXISTS_EMAIL.isFalse(hasEmail);

		UserDO userDO = userConvert.buildEntity(command);
		userRepository.save(userDO);

		return userDO.getUserId();
	}
}
