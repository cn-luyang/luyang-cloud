package io.github.luyang.platform.open.token.service;

import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.repository.TokenRepository;
import io.github.luyang.platform.open.token.repository.entity.TokenEntity;
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

		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setClientId(createTokenBO.getClientId());
		tokenEntity.setUserId(createTokenBO.getUserId());
		tokenEntity.setUserInfo(null);
		tokenEntity.setAccessToken(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN));
		tokenEntity.setRefreshToken(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN));
		LocalDateTime now = LocalDateTime.now();
		tokenEntity.setAccessTokenExpiresTime(now.plusSeconds(createTokenBO.getAccessTokenValidity()));
		tokenEntity.setRefreshTokenExpiresTime(now.plusSeconds(createTokenBO.getRefreshTokenValidity()));
		tokenRepository.save(tokenEntity);

		return CreateTokenDTO.builder()
			.accessToken(tokenEntity.getAccessToken())
			.refreshToken(tokenEntity.getRefreshToken())
			.build();
	}
}
