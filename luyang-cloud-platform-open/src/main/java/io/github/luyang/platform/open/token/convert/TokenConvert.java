package io.github.luyang.platform.open.token.convert;

import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import io.github.luyang.platform.open.token.service.model.CreateTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateTokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(
	componentModel = "spring",
	imports = {LocalDateTime.class}
)
public interface TokenConvert {

	@Mapping(target = "nowTime", expression = "java(LocalDateTime.now())")
	TokenRenewalQuery convertTokenRenewalQuery(CreateTokenBO createTokenBO);

	CreateTokenDTO convertToCreateTokenDTO(TokenDO tokenDO);

	default TokenRenewalOps convertTokenRenewalOps(CreateTokenBO createTokenBO, TokenDO tokenDO) {

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

	default TokenDO convertTokenDO(CreateTokenBO createTokenBO) {
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
