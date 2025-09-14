package io.github.luyang.platform.uac.service.impl;

import io.github.luyang.platform.uac.beans.convert.UserConvert;
import io.github.luyang.platform.uac.beans.entity.UserEntity;
import io.github.luyang.platform.uac.beans.request.UserCreateRequest;
import io.github.luyang.platform.uac.enums.error.UserError;
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
	public String create(UserCreateRequest request) {

		boolean hasEmail = userRepository.existsEmail(request.email());
		UserError.EXISTS_EMAIL.isFalse(hasEmail);

		UserEntity entity = userConvert.buildEntity(request);
		userRepository.save(entity);

		return entity.getUserId();
	}
}
