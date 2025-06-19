package io.github.luyang.platform.open.token.service;

import io.github.luyang.platform.open.token.convert.TokenConvert;
import io.github.luyang.platform.open.token.repository.TokenRepository;
import io.github.luyang.platform.open.token.repository.entity.TokenEntity;
import io.github.luyang.platform.open.token.service.bo.CreateTokenBO;
import io.github.luyang.platform.open.token.service.dto.CreateTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

		TokenEntity tokenEntity = tokenConvert.toEntity(createTokenBO);
		tokenRepository.save(tokenEntity);

		return CreateTokenDTO.builder()
			.accessToken(tokenEntity.getAccessToken())
			.refreshToken(tokenEntity.getRefreshToken())
			.build();
	}
}
