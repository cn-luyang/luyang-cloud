package io.github.luyang.platform.uaa.token;

import io.github.luyang.platform.uaa.token.beans.TokenConvert;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateParam;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateResult;
import io.github.luyang.platform.uaa.token.beans.entity.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	private final TokenConvert tokenConvert;

	public TokenCreateResult create(TokenCreateParam param) {

		// 移除同一客户端和用户下的旧 Token
		this.tokenRepository.removeByClientIdAndUserId(param.clientId(), param.userId());

		// 构建并持久化 Token 实体
		TokenEntity entity = tokenConvert.buildEntity(param);
		tokenRepository.save(entity);

		return tokenConvert.buildTokenCreateResult(entity);
	}
}
