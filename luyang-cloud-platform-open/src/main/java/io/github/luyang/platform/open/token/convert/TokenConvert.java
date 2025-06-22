package io.github.luyang.platform.open.token.convert;

import io.github.luyang.api.uac.result.GetUserResult;
import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import io.github.luyang.platform.open.token.service.model.CreateUserTokenBO;
import io.github.luyang.platform.open.token.service.model.CreateUserTokenDTO;
import io.github.luyang.starter.security.SecurityUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(
	componentModel = "spring",
	imports = {LocalDateTime.class}
)
public interface TokenConvert {

	@Mapping(target = "nowTime", expression = "java(LocalDateTime.now())")
	TokenRenewalQuery convertTokenRenewalQuery(CreateUserTokenBO createUserTokenBO);

	CreateUserTokenDTO convertToCreateUserTokenDTO(TokenDO tokenDO);

	default TokenRenewalOps convertTokenRenewalOps(CreateUserTokenBO createUserTokenBO, TokenDO tokenDO) {

		TokenRenewalOps tokenRenewalOps = new TokenRenewalOps();
		tokenRenewalOps.setId(tokenDO.getId());

		LocalDateTime accessTokenExpiresTime = tokenDO.getAccessTokenExpiresTime();
		Integer accessTokenValidity = createUserTokenBO.getAccessTokenValidity();
		tokenRenewalOps.setAccessTokenExpiresTime(accessTokenExpiresTime.plusSeconds(accessTokenValidity));

		LocalDateTime refreshTokenExpiresTime = tokenDO.getRefreshTokenExpiresTime();
		Integer refreshTokenValidity = createUserTokenBO.getRefreshTokenValidity();
		tokenRenewalOps.setRefreshTokenExpiresTime(refreshTokenExpiresTime.plusSeconds(refreshTokenValidity));

		return tokenRenewalOps;
	}

	default TokenDO convertTokenDO(CreateUserTokenBO createUserTokenBO) {
		TokenDO tokenDO = new TokenDO();
		tokenDO.setClientId(createUserTokenBO.getClientId());
		tokenDO.setUserId(createUserTokenBO.getUserId());
		tokenDO.setAttachedInfo(null);
		tokenDO.setAccessToken(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN));
		tokenDO.setRefreshToken(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN));
		LocalDateTime now = LocalDateTime.now();
		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(createUserTokenBO.getAccessTokenValidity()));
		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(createUserTokenBO.getRefreshTokenValidity()));
		return tokenDO;
	}

	SecurityUser convertToSecurityUser(String clientId, GetUserResult userResult);
}
