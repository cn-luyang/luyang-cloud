package io.github.luyang.platform.open.token.convert;

import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import io.github.luyang.platform.open.token.service.model.CreateTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateTokenDTO;

import java.time.LocalDateTime;

public class TokenConvert {

	public static TokenRenewalQuery convertTokenRenewalQuery(CreateTokenBO createTokenBO) {
		TokenRenewalQuery tokenRenewalQuery = new TokenRenewalQuery();
		tokenRenewalQuery.setClientId(createTokenBO.getClientId());
		tokenRenewalQuery.setUserId(createTokenBO.getUserId());
		tokenRenewalQuery.setNowTime(LocalDateTime.now());
		return tokenRenewalQuery;
	}

	public static TokenRenewalOps convertTokenRenewalOps(CreateTokenBO createTokenBO, TokenDO tokenDO) {

		TokenRenewalOps tokenRenewalOps = new TokenRenewalOps();
		tokenRenewalOps.setId(tokenDO.getId());

		LocalDateTime accessTokenExpiresTime = tokenDO.getAccessTokenExpiresTime();
		Integer accessTokenValidity = createTokenBO.getAccessTokenValidity();
		tokenRenewalOps.setAccessTokenExpiresTime(accessTokenExpiresTime.plusSeconds(accessTokenValidity));

		LocalDateTime refreshTokenExpiresTime = tokenDO.getRefreshTokenExpiresTime();
		Integer refreshTokenValidity = createTokenBO.getRefreshTokenValidity();
		tokenRenewalOps.setRefreshTokenExpiresTime(refreshTokenExpiresTime.plusSeconds(refreshTokenValidity));

		return tokenRenewalOps;
	}

	public static CreateTokenDTO convertCreateTokenDTO(TokenDO tokenDO) {
		CreateTokenDTO createTokenDTO = new CreateTokenDTO();
		createTokenDTO.setAccessToken(tokenDO.getAccessToken());
		createTokenDTO.setRefreshToken(tokenDO.getRefreshToken());
		return createTokenDTO;
	}

	public static TokenDO convertTokenDO(CreateTokenBO createTokenBO) {
		TokenDO tokenDO = new TokenDO();
		tokenDO.setClientId(createTokenBO.getClientId());
		tokenDO.setUserId(createTokenBO.getUserId());
		tokenDO.setAttachedInfo(null);
		tokenDO.setAccessToken(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN));
		tokenDO.setRefreshToken(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN));
		LocalDateTime now = LocalDateTime.now();
		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(createTokenBO.getAccessTokenValidity()));
		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(createTokenBO.getRefreshTokenValidity()));
		return tokenDO;
	}
}
