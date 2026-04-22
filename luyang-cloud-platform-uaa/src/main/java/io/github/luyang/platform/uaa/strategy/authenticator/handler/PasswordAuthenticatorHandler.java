package io.github.luyang.platform.uaa.strategy.authenticator.handler;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthCMD;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthResult;
import io.github.luyang.platform.uaa.strategy.authenticator.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 密码认证处理器
 *
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

	@Override
	public AccountAuthResult authenticate(AccountAuthCMD accountAuthCMD) {
		return new AccountAuthResult(IdUtil.fastSimpleUUID(), "张三");
	}
}
