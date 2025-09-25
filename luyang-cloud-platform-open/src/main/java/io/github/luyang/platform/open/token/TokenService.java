package io.github.luyang.platform.open.token;

import io.github.luyang.platform.open.token.bo.TokenCreateParam;
import io.github.luyang.platform.open.token.bo.TokenCreateResult;
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

	public TokenCreateResult createToken (TokenCreateParam param) {

		// 移除同一客户端和用户下的旧 Token
		this.tokenRepository.removeByClientIdAndUserId(param.clientId(), param.userId());

		// 构建并持久化 Token 实体
		TokenEntity entity = tokenConvert.buildEntity(param);
		tokenRepository.save(entity);

		return tokenConvert.buildTokenCreateResult(entity);
	}
}
