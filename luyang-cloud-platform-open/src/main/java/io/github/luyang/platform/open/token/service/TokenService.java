package io.github.luyang.platform.open.token.service;

import io.github.luyang.api.uac.RemoteUserService;
import io.github.luyang.api.uac.param.GetUserParam;
import io.github.luyang.api.uac.result.GetUserResult;
import io.github.luyang.platform.open.token.convert.TokenConvert;
import io.github.luyang.platform.open.token.repository.TokenRepository;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import io.github.luyang.platform.open.token.service.model.CreateUserTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateUserTokenDTO;
import io.github.luyang.starter.base.api.Result;
import io.github.luyang.starter.base.api.ResultOps;
import io.github.luyang.starter.base.error.BusinessException;
import io.github.luyang.starter.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * Token 相关服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenService {

	@DubboReference
	private RemoteUserService remoteUserService;

	private final TokenConvert tokenConvert;
	private final TokenRepository tokenRepository;

	public CreateUserTokenDTO createUserToken(CreateUserTokenBO createUserTokenBO) {

		// 构造续期查询参数
		TokenRenewalQuery tokenRenewalQuery = tokenConvert.convertTokenRenewalQuery(createUserTokenBO);
		TokenDO tokenDO = tokenRepository.find(tokenRenewalQuery);

		if (null != tokenDO) {
			// 续期Token
			TokenRenewalOps tokenRenewalOps = tokenConvert.convertTokenRenewalOps(createUserTokenBO, tokenDO);
			tokenRepository.modify(tokenRenewalOps);
		} else {
			// 创建新Token
			tokenDO = tokenConvert.convertTokenDO(createUserTokenBO);
			this.buildAttachedInfo(tokenDO);
			tokenRepository.save(tokenDO);

		}

		return tokenConvert.convertToCreateUserTokenDTO(tokenDO);
	}

	private void buildAttachedInfo(TokenDO tokenDO) {

		Result<GetUserResult> result = remoteUserService.getUser(
			GetUserParam.builder().userId(tokenDO.getUserId()).build()
		);

		GetUserResult userResult = ResultOps.of(result)
			.assertSuccess(() -> new BusinessException(result.getCode(), result.getMessage()))
			.getData()
			.orElse(GetUserResult.builder().build());

		SecurityUser securityUser = tokenConvert.convertToSecurityUser(tokenDO.getClientId(), userResult);
		tokenDO.setAttachedInfo(securityUser);
	}
}
