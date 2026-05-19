package io.github.luyang.platform.uac.service.impl;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uac.beans.UserEntity;
import io.github.luyang.platform.uac.beans.command.CreateAccountCommand;
import io.github.luyang.platform.uac.beans.command.CreateUserCommand;
import io.github.luyang.platform.uac.beans.convert.UserConvert;
import io.github.luyang.platform.uac.repository.UserRepository;
import io.github.luyang.platform.uac.service.AccountService;
import io.github.luyang.platform.uac.service.PasswordService;
import io.github.luyang.platform.uac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	@Transactional(rollbackFor = Exception.class)
	public String createUser(CreateUserCommand command) {

		String userId = "u_" + IdUtil.nanoId();

		// 创建账号信息
		List<CreateAccountCommand> createAccountCommands = userConvert.buildCreateAccountCommand(userId, command);
		accountService.createAccount(createAccountCommands);

		// 创建用户信息
		UserEntity userEntity = userConvert.buildEntity(userId, command);
		userRepository.save(userEntity);

		return userId;
	}
}
