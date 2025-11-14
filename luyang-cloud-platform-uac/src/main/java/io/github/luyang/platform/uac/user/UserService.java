package io.github.luyang.platform.uac.user;

import io.github.luyang.platform.uac.common.enums.error.UserError;
import io.github.luyang.platform.uac.user.beans.UserConvert;
import io.github.luyang.platform.uac.user.beans.UserDomain;
import io.github.luyang.platform.uac.user.beans.body.UserCreateRequest;
import io.github.luyang.platform.uac.user.beans.body.UserCreateResponse;
import io.github.luyang.platform.uac.user.beans.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserConvert userConvert;
	private final UserRepository userRepository;

	public UserCreateResponse create(UserCreateRequest userCreateRequest) {

		boolean hasEmail = userRepository.existsByEmail(userCreateRequest.email());
		UserError.EXISTS_EMAIL.isFalse(hasEmail);

		UserEntity entity = userConvert.buildEntity(userCreateRequest);
		userRepository.save(entity);

		return UserCreateResponse.build(entity.getUserId());
	}

	public UserDomain getByEmail(String email) {
		return null;
	}
}
