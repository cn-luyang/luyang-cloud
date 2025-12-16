package io.github.luyang.platform.uac.user.remote;

import cn.hutool.core.lang.Validator;
import io.github.luyang.api.uac.UserRemoteService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uac._common.enums.error.UserError;
import io.github.luyang.platform.uac.user.UserService;
import io.github.luyang.platform.uac.user.beans.UserDomain;
import io.github.luyang.starter.base.model.Result;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author yang.lu
 */
@DubboService
public record UserRemoteServiceImpl(UserService userService) implements UserRemoteService {

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
