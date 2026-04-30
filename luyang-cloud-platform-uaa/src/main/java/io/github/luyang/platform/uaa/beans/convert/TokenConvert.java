package io.github.luyang.platform.uaa.beans.convert;

import io.github.luyang.platform.uaa.beans.TokenDO;
import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.common.util.TokenUtil;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

/**
 * Token 对象转换器
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface TokenConvert {

	default TokenDO buildEntity(ClientDomain clientDomain) {
		LocalDateTime now = LocalDateTime.now();

		TokenDO tokenDO = new TokenDO();
		tokenDO.setUserId("");
		tokenDO.setAccessToken(TokenUtil.generateToken(TokenUtil.TokenStyle.ACCESS_TOKEN));
		tokenDO.setRefreshToken(TokenUtil.generateToken(TokenUtil.TokenStyle.REFRESH_TOKEN));
		tokenDO.setTokenIssuedTime(now);
		tokenDO.setAccessTokenExpiresTime(now.plusSeconds(clientDomain.accessTokenValidity()));
		tokenDO.setRefreshTokenExpiresTime(now.plusSeconds(clientDomain.refreshTokenValidity()));
		tokenDO.setExtraInfo(null);
		return tokenDO;
	}
}
