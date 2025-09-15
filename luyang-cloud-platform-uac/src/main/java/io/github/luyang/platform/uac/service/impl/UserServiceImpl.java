package io.github.luyang.platform.uac.service.impl;

import io.github.luyang.platform.uac.beans.convert.UserConvert;
import io.github.luyang.platform.uac.beans.domain.UserDomain;
import io.github.luyang.platform.uac.beans.entity.UserEntity;
import io.github.luyang.platform.uac.beans.enums.error.UserError;
import io.github.luyang.platform.uac.beans.request.UserCreateReq;
import io.github.luyang.platform.uac.repository.UserRepository;
import io.github.luyang.platform.uac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserConvert userConvert;
	private final UserRepository userRepository;

	@Override
	public String create(UserCreateReq userCreateReq) {

		boolean hasEmail = userRepository.existsEmail(userCreateReq.email());
		UserError.EXISTS_EMAIL.isFalse(hasEmail);

		UserEntity entity = userConvert.buildEntity(userCreateReq);
		userRepository.save(entity);

		return entity.getUserId();
	}

	@Override
	public UserDomain getByEmail(String email) {
		UserEntity entity = this.userRepository.findByEmail(email);
		return userConvert.buildDomain(entity);
	}
}
