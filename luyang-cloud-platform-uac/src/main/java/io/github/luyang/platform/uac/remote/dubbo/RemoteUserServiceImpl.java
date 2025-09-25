package io.github.luyang.platform.uac.remote.dubbo;

import cn.hutool.core.lang.Validator;
import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uac.common.enums.error.UserError;
import io.github.luyang.platform.uac.service.UserService;
import io.github.luyang.platform.uac.service.domain.UserDomain;
import io.github.luyang.starter.base.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author yang.lu
 */
@DubboService
@RequiredArgsConstructor
public class RemoteUserServiceImpl implements RemoteUserService {

	private final UserService userService;

	@Override
	public Result<AccountAuthResponse> accountAuth(AccountAuthRequest accountAuthRequest) {

		String account = accountAuthRequest.account();

		// 校验邮箱格式
		boolean isEmail = Validator.isEmail(account);
		UserError.INVALID_EMAIL_FORMAT.isTrue(isEmail);

		// 通过邮箱查询用户信息
		UserDomain userDomain = userService.getByEmail(account);
		UserError.NOT_FOUND_USER.notNull(userDomain);

		return Result.success(
			new AccountAuthResponse(
				userDomain.userId(),
				userDomain.email(),
				userDomain.zhName(),
				userDomain.enName()
			)
		);
	}
}
