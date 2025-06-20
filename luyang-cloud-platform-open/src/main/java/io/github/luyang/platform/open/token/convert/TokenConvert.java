package io.github.luyang.platform.open.token.convert;

import org.mapstruct.Mapper;

@Mapper
public interface TokenConvert {

//	default TokenEntity toEntity(CreateTokenBO createTokenBO) {
//		if (null == createTokenBO) {
//			return null;
//		}
//
//		TokenEntity tokenEntity = new TokenEntity();
//		tokenEntity.setClientId(createTokenBO.getClientId());
//		tokenEntity.setUserId(null);
//		tokenEntity.setUserInfo(null);
//		tokenEntity.setAccessToken(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN));
//		LocalDateTime now = LocalDateTime.now();
//		tokenEntity.setAccessTokenExpiresTime(now.plusSeconds(createTokenBO.getAccessTokenValidity()));
//
//		if (!createTokenBO.isClientAuth()) {
//			tokenEntity.setRefreshToken(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN));
//			tokenEntity.setRefreshTokenExpiresTime(now.plusSeconds(createTokenBO.getRefreshTokenValidity()));
//		}
//
//		return tokenEntity;
//	}
}
