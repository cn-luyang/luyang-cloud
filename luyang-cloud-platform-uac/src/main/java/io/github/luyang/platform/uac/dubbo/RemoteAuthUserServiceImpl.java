package io.github.luyang.platform.uac.dubbo;

import cn.hutool.core.bean.BeanUtil;
import io.github.luyang.api.uac.RemoteAuthUserService;
import io.github.luyang.api.uac.model.GetAuthUserDTO;
import io.github.luyang.api.uac.model.GetAuthUserResult;
import io.github.luyang.platform.uac.base.valueobject.Email;
import io.github.luyang.platform.uac.user.repository.entity.UserEntity;
import io.github.luyang.platform.uac.user.service.UserService;
import io.github.luyang.starter.base.api.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yang.lu
 */
//@DubboService
@RequiredArgsConstructor
public class RemoteAuthUserServiceImpl implements RemoteAuthUserService {

	private final UserService userService;
	private final PasswordEncoder bCryptPasswordEncoder;

	@Override
	public Result<GetAuthUserResult> getAuthUser(GetAuthUserDTO getAuthUserDTO) {

		Email email = Email.build(getAuthUserDTO.getAccount());
		UserEntity userEntity = null;
		if (email.checkFormat()) {
			userEntity = userService.getUser(email);
		}

		if (null != userEntity && getAuthUserDTO.isVerifySecret()) {
			bCryptPasswordEncoder.matches(getAuthUserDTO.getSecret(), userEntity.getPassword());
		}

		String userIdStr = BeanUtil.isEmpty(userEntity) ? null : userEntity.getUserId();

		return Result.success(GetAuthUserResult.builder()
			.userId(userIdStr)
			.build());
	}
}
