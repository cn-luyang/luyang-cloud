package io.github.luyang.platform.open.rpc;

import io.github.luyang.platform.open.token.domain.TokenDomain;
import io.github.luyang.platform.open.token.service.TokenService;
import io.github.luyang.starter.base.api.Result;
import io.github.luyang.starter.security.UnifiedPrincipal;
import io.github.luyang.starter.security.constant.enums.PrincipalType;
import io.github.luyang.starter.security.rpc.TokenValidationRpc;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author yang.lu
 */
@DubboService
@RequiredArgsConstructor
public class TokenValidationRpcImpl implements TokenValidationRpc {

	private final TokenService tokenService;

	@Override
	public Result<UnifiedPrincipal> validateToken(String accessToken) {
		TokenDomain tokenDomain = tokenService.getByAccessToken(accessToken);
		return Result.success(new UnifiedPrincipal(
			tokenDomain.clientId(),
			tokenDomain.userId(),
			PrincipalType.USER,
			tokenDomain.attachedInfoMap())
		);
	}
}
