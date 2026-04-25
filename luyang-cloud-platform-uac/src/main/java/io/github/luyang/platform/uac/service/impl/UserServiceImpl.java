package io.github.luyang.platform.uac.service.impl;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uac.beans.UserDO;
import io.github.luyang.platform.uac.beans.convert.UserConvert;
import io.github.luyang.platform.uac.beans.payload.CreateUserDTO;
import io.github.luyang.platform.uac.beans.payload.CreateUserVO;
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
	public CreateUserVO create(CreateUserDTO createUserDTO) {
		boolean hasEmail = userRepository.emailUnique(createUserDTO.email());
		ErrorCode.USER_EXISTS_EMAIL.isFalse(hasEmail);

		String password = createUserDTO.password();
		String confirmPassword = createUserDTO.confirmPassword();
		ErrorCode.USER_PASSWORD_MISMATCH.isTrue(StrUtil.equals(password, confirmPassword));

		UserDO userDO = userConvert.buildEntity(createUserDTO);
		userRepository.save(userDO);

		return CreateUserVO.build(userDO.getUserId());
	}
}
