package io.github.luyang.platform.uac.dubbo;

import cn.hutool.core.lang.Validator;
import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.request.AccountAuthRequest;
import io.github.luyang.api.uac.response.AccountAuthResponse;
import io.github.luyang.platform.uac.beans.domain.UserDomain;
import io.github.luyang.platform.uac.beans.enums.error.UserError;
import io.github.luyang.platform.uac.service.UserService;
import io.github.luyang.starter.base.api.Result;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author yang.lu
 */
@DubboService
public record RemoteUserServiceImpl(UserService userService) implements RemoteUserService {

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
