package io.github.luyang.platform.open.token.convert;

import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.repository.entity.TokenEntity;
import io.github.luyang.platform.open.token.service.bo.CreateTokenBO;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface TokenConvert {

	default TokenEntity toEntity(CreateTokenBO createTokenBO) {
		if (null == createTokenBO) {
			return null;
		}

		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setClientId(createTokenBO.getClientId());
		tokenEntity.setUserId(null);
		tokenEntity.setUserInfo(null);
		tokenEntity.setAccessToken(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN));
		LocalDateTime now = LocalDateTime.now();
		tokenEntity.setAccessTokenExpiresTime(now.plusSeconds(createTokenBO.getAccessTokenValidity()));

		if (!createTokenBO.isClientAuth()) {
			tokenEntity.setRefreshToken(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN));
			tokenEntity.setRefreshTokenExpiresTime(now.plusSeconds(createTokenBO.getRefreshTokenValidity()));
		}

		return tokenEntity;
	}
}
