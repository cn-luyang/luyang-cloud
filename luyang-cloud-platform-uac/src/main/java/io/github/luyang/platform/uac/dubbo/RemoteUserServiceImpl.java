package io.github.luyang.platform.uac.dubbo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.model.VerifyAccountParam;
import io.github.luyang.api.uac.model.VerifyAccountResult;
import io.github.luyang.platform.uac.base.enums.error.UserError;
import io.github.luyang.platform.uac.base.valueobject.Email;
import io.github.luyang.platform.uac.user.repository.entity.UserEntity;
import io.github.luyang.platform.uac.user.service.UserService;
import io.github.luyang.starter.base.api.Result;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yang.lu
 */
@DubboService
@RequiredArgsConstructor
public class RemoteUserServiceImpl implements RemoteUserService {

	private final UserService userService;
	private final PasswordEncoder bCryptPasswordEncoder;

	@Override
	public Result<VerifyAccountResult> verifyAccount(VerifyAccountParam verifyAccountParam) {

		String account = verifyAccountParam.getAccount();
		if (!Validator.isEmail(account)) {
			return Result.failure(UserError.INVALID_EMAIL_FORMAT);
		}

		UserEntity userEntity = userService.getUser(Email.build(account));
		if (BeanUtil.isEmpty(userEntity)) {
			return Result.failure(UserError.NOT_FOUND_USER);
		}

//		boolean matches = bCryptPasswordEncoder.matches(verifyAccountParam.getSecret(), userEntity.getPassword());
//		if (!matches) {
//			return Result.failure(UserError.INVALID_PASSWORD);
//		}

		return Result.success(VerifyAccountResult.builder()
			.userId(userEntity.getUserId())
			.build());
	}
}
