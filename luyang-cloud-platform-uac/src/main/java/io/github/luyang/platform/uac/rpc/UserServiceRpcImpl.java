package io.github.luyang.platform.uac.rpc;

import cn.hutool.core.lang.Validator;
import io.github.luyang.api.uac.UserServiceRpc;
import io.github.luyang.api.uac.dto.AccountAuthDTO;
import io.github.luyang.api.uac.param.AccountAuthParam;
import io.github.luyang.platform.uac.base.enums.error.UserError;
import io.github.luyang.platform.uac.user.domain.UserDomain;
import io.github.luyang.platform.uac.user.service.UserService;
import io.github.luyang.starter.base.api.Result;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 用户RPC服务实现类
 *
 * @author yang.lu
 */
@DubboService
@RequiredArgsConstructor
public class UserServiceRpcImpl implements UserServiceRpc {

	private final UserService userService;

	@Override
	public Result<AccountAuthDTO> accountAuth(AccountAuthParam accountAuthParam) {

		String account = accountAuthParam.account();

		boolean isEmail = Validator.isEmail(account);
		UserError.INVALID_EMAIL_FORMAT.isTrue(isEmail);

		UserDomain userDomain = userService.getUserByEmail(account);
		UserError.NOT_FOUND_USER.notNull(userDomain);

		if (userDomain.passwordIsNotEmpty()) {
			boolean passwordSame = userDomain.passwordMatches(accountAuthParam.secret());
			UserError.INVALID_PASSWORD.isTrue(passwordSame);
		}

		return Result.success(
			new AccountAuthDTO(
				userDomain.userId(),
				userDomain.email(),
				userDomain.zhName(),
				userDomain.enName()
			)
		);
	}
}
