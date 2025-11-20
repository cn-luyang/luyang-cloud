package io.github.luyang.platform.uaa.token.remote;

import io.github.luyang.starter.base.common.model.Result;
import io.github.luyang.starter.security.AuthUser;
import io.github.luyang.starter.security.remote.SecurityTokenRpc;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author yang.lu
 */
@DubboService
public class SecurityTokenRpcImpl implements SecurityTokenRpc {

	@Override
	public Result<AuthUser> checkToken(String accessToken) {
		return null;
	}
}
