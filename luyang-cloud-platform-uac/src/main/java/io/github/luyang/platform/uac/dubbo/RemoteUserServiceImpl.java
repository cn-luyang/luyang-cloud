package io.github.luyang.platform.uac.dubbo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.param.GetUserParam;
import io.github.luyang.api.uac.param.VerifyAccountParam;
import io.github.luyang.api.uac.result.GetUserResult;
import io.github.luyang.api.uac.result.VerifyAccountResult;
import io.github.luyang.platform.uac.base.enums.error.UserError;
import io.github.luyang.platform.uac.user.repository.UserRepository;
import io.github.luyang.platform.uac.user.repository.entity.UserDO;
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

	private final UserRepository userRepository;
	private final PasswordEncoder bCryptPasswordEncoder;

	@Override
	public Result<VerifyAccountResult> verifyAccount(VerifyAccountParam verifyAccountParam) {

		String account = verifyAccountParam.getAccount();
		if (!Validator.isEmail(account)) {
			return Result.failure(UserError.INVALID_EMAIL_FORMAT);
		}

		UserDO userDO = userRepository.findByEmail(account);
		if (BeanUtil.isEmpty(userDO)) {
			return Result.failure(UserError.NOT_FOUND_USER);
		}

		return Result.success(VerifyAccountResult.builder()
			.userId(userDO.getUserId())
			.build());
	}

	@Override
	public Result<GetUserResult> getUser(GetUserParam getUserParam) {

		UserDO userDO = userRepository.lambdaQuery()
			.eq(StrUtil.isNotEmpty(getUserParam.getUserId()), UserDO::getUserId, getUserParam.getUserId())
			.eq(StrUtil.isNotEmpty(getUserParam.getEmail()), UserDO::getEmail, getUserParam.getEmail())
			.one();

		if (BeanUtil.isEmpty(userDO)) {
			return Result.failure(UserError.NOT_FOUND_USER);
		}

		return Result.success(GetUserResult.builder()
			.userId(userDO.getUserId())
			.email(userDO.getEmail())
			.zhName(userDO.getZhName())
			.enName(userDO.getEnName())
			.build());
	}
}
