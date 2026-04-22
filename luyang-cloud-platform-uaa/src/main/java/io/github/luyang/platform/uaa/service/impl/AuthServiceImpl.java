package io.github.luyang.platform.uaa.service.impl;

import io.github.luyang.platform.uaa.beans.contract.AccountAuthCMD;
import io.github.luyang.platform.uaa.beans.contract.AccountAuthResult;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateCMD;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateResult;
import io.github.luyang.platform.uaa.beans.convert.AuthConvert;
import io.github.luyang.platform.uaa.beans.payload.LoginDTO;
import io.github.luyang.platform.uaa.beans.payload.LoginVO;
import io.github.luyang.platform.uaa.service.AuthService;
import io.github.luyang.platform.uaa.service.TokenService;
import io.github.luyang.platform.uaa.strategy.authenticator.AuthenticatorContext;
import io.github.luyang.platform.uaa.strategy.authenticator.AuthenticatorHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final TokenService tokenService;
	private final AuthConvert authConvert;

	@Override
	public LoginVO login(LoginDTO loginDTO) {

		AuthenticatorHandler authenticatorHandler = AuthenticatorContext.getAuthenticator(loginDTO.loginMethod());

		AccountAuthCMD accountAuthCMD = authConvert.buildAccountAuthCMD(loginDTO);
		AccountAuthResult accountAuthResult = authenticatorHandler.authenticate(accountAuthCMD);

		TokenCreateCMD tokenCreateCMD = authConvert.buildTokenCreateCMD(accountAuthResult);
		TokenCreateResult tokenCreateResult = tokenService.create(tokenCreateCMD);

		return authConvert.buildLoginVO(tokenCreateResult, accountAuthResult);
	}
}
