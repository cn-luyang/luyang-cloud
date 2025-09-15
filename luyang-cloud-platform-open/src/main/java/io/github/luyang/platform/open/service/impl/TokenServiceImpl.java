package io.github.luyang.platform.open.service.impl;

import io.github.luyang.platform.open.beans.convert.TokenConvert;
import io.github.luyang.platform.open.beans.domain.TokenDomain;
import io.github.luyang.platform.open.beans.entity.TokenEntity;
import io.github.luyang.platform.open.beans.param.UserTokenCreateParam;
import io.github.luyang.platform.open.repository.TokenRepository;
import io.github.luyang.platform.open.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenConvert tokenConvert;
	private final TokenRepository tokenRepository;

	public TokenDomain createUserToken(UserTokenCreateParam param) {

		// 移除同一客户端和用户下的旧 Token
		this.tokenRepository.removeByClientIdAndUserId(param.clientId(), param.userId());

		// 构建并持久化 Token 实体
		TokenEntity entity = tokenConvert.buildEntity(param);
		tokenRepository.save(entity);

		return tokenConvert.buildDomain(entity);
	}
}
