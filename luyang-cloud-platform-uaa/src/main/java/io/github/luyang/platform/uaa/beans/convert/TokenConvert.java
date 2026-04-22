package io.github.luyang.platform.uaa.beans.convert;

import io.github.luyang.platform.uaa.beans.TokenDO;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateCMD;
import io.github.luyang.platform.uaa.beans.contract.TokenCreateResult;
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

	default TokenDO buildEntity(TokenCreateCMD tokenCreateCMD) {
		LocalDateTime now = LocalDateTime.now();

		TokenDO tokenDO = new TokenDO();
		tokenDO.setUserId(tokenCreateCMD.userId());
		tokenDO.setAccessToken(TokenUtil.generateToken(TokenUtil.TokenStyle.ACCESS_TOKEN));
		tokenDO.setRefreshToken(TokenUtil.generateToken(TokenUtil.TokenStyle.REFRESH_TOKEN));
//		tokenDO.setIdToken();
		tokenDO.setTokenIssuedTime(now);
		tokenDO.setAccessTokenExpiresTime(now.plusHours(2));
		tokenDO.setRefreshTokenExpiresTime(now.plusDays(7));
		return tokenDO;
	}

	TokenCreateResult buildTokenCreateResult(TokenDO tokenDO);
}
