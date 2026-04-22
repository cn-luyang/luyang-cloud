package io.github.luyang.platform.uaa.service.impl;

import io.github.luyang.platform.uaa.beans.TokenDO;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateCMD;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateResult;
import io.github.luyang.platform.uaa.beans.convert.TokenConvert;
import io.github.luyang.platform.uaa.repository.TokenRepository;
import io.github.luyang.platform.uaa.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenRepository tokenRepository;
	private final TokenConvert tokenConvert;

	@Override
	public TokenCreateResult create(TokenCreateCMD tokenCreateCMD) {
		TokenDO tokenDO = tokenConvert.buildEntity(tokenCreateCMD);
		tokenRepository.save(tokenDO);
		return tokenConvert.buildTokenCreateResult(tokenDO);
	}
}
