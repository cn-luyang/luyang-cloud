package io.github.luyang.platform.open.token.service;

import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.repository.TokenRepository;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.service.bo.CreateTokenBO;
import io.github.luyang.platform.open.token.service.dto.CreateTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Token 相关服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;

	public CreateTokenDTO createToken(CreateTokenBO createTokenBO) {

		TokenDO tokenDO = new TokenDO();
		tokenDO.setClientId(createTokenBO.getClientId());
		tokenDO.setUserId(createTokenBO.getUserId());
		tokenDO.setAttachedInfo(null);
		tokenDO.setAccessToken(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN));
		tokenDO.setRefreshToken(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN));
		LocalDateTime now = LocalDateTime.now();
		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(createTokenBO.getAccessTokenValidity()));
		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(createTokenBO.getRefreshTokenValidity()));
		tokenRepository.save(tokenDO);

		return CreateTokenDTO.builder()
			.accessToken(tokenDO.getAccessToken())
			.refreshToken(tokenDO.getRefreshToken())
			.build();
	}
}
