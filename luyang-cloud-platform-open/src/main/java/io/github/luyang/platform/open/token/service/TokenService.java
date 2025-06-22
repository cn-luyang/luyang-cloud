package io.github.luyang.platform.open.token.service;

import io.github.luyang.platform.open.token.convert.TokenConvert;
import io.github.luyang.platform.open.token.repository.TokenRepository;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import io.github.luyang.platform.open.token.service.model.CreateTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Token 相关服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenConvert tokenConvert;
	private final TokenRepository tokenRepository;

	public CreateTokenDTO createToken(CreateTokenBO createTokenBO) {

		// 构造续期查询参数，查询当前是否存在可续期的 Token
		TokenRenewalQuery tokenRenewalQuery = tokenConvert.convertTokenRenewalQuery(createTokenBO);
		Optional<TokenDO> tokenDOOpt = tokenRepository.find(tokenRenewalQuery);
		if (tokenDOOpt.isPresent()) {
			// 如果存在旧令牌，尝试进行续期操作
			TokenDO tokenDO = tokenDOOpt.get();
			TokenRenewalOps tokenRenewalOps = tokenConvert.convertTokenRenewalOps(createTokenBO, tokenDO);
			tokenRepository.modify(tokenRenewalOps);

			return tokenConvert.convertToCreateTokenDTO(tokenDO);
		}

		TokenDO tokenDO = tokenConvert.convertTokenDO(createTokenBO);
		tokenRepository.save(tokenDO);

		return tokenConvert.convertToCreateTokenDTO(tokenDO);
	}
}
