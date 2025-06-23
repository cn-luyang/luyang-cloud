package io.github.luyang.platform.uac.user.service.impl;

import cn.hutool.core.lang.Validator;
import io.github.luyang.platform.uac.base.converter.UserConvert;
import io.github.luyang.platform.uac.base.enums.error.UserError;
import io.github.luyang.platform.uac.user.domain.UserCommand;
import io.github.luyang.platform.uac.user.domain.UserDomain;
import io.github.luyang.platform.uac.user.repository.UserRepository;
import io.github.luyang.platform.uac.user.repository.model.UserDO;
import io.github.luyang.platform.uac.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserConvert convert;
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDomain create(UserCommand command) {

		String email = command.email();

		boolean isEmail = Validator.isEmail(email);
		UserError.INVALID_EMAIL_FORMAT.isTrue(isEmail);

		boolean hasEmail = this.repository.existsEmail(email);
		UserError.EXISTS_EMAIL.isFalse(hasEmail);

		UserDO userDO = this.convert.toDO(command, passwordEncoder);
		this.repository.save(userDO);

		return this.convert.toDomain(userDO);
	}

	@Override
	public UserDomain getUserByEmail(String email) {
		UserDO userDO = this.repository.findByEmail(email);
		return this.convert.toDomain(userDO);
	}
}
