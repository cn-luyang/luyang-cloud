package io.github.luyang.platform.uaa.strategy.authenticator;

import io.github.luyang.platform.uaa.beans.contract.AccountAuthCMD;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthResult;

/**
 * 认证处理器接口
 *
 * @author yang.lu
 */
public interface AuthenticatorHandler {

	AccountAuthResult authenticate(AccountAuthCMD accountAuthCMD);
}
