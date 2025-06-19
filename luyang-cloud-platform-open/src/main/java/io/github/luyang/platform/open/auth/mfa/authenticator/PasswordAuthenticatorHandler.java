package io.github.luyang.platform.open.auth.mfa.authenticator;

import io.github.luyang.platform.open.auth.controller.request.LoginRequest;
import io.github.luyang.platform.open.auth.mfa.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class PasswordAuthenticatorHandler implements AuthenticatorHandler {

//	@DubboReference
//	private final RemoteAuthUserService remoteAuthUserService;

	@Override
	public String authenticate(LoginRequest request) {

//		GetAuthUserDTO getAuthUserDTO = GetAuthUserDTO.builder()
//			.account(request.getAccount())
//			.secret(request.getSecret())
//			.verifySecret(true)
//			.build();
//
//		Result<GetAuthUserResult> authUser = remoteAuthUserService.getAuthUser(getAuthUserDTO);
//		return ResultOps.of(authUser)
//			.assertSuccess(() -> new BusinessException(authUser.getMessage()))
//			.getData()
//			.map(GetAuthUserResult::getUserId)
//			.orElse(null);
		return null;
	}
}
