package io.github.luyang.platform.uaa.token.beans;

import io.github.luyang.platform.uaa._common.enums.TokenStyleEnum;
import io.github.luyang.platform.uaa._common.util.TokenUtil;
import io.github.luyang.platform.uaa.token.beans.bo.OAuth2TokenCreateParam;
import io.github.luyang.platform.uaa.token.beans.bo.OAuth2TokenCreateResult;
import io.github.luyang.platform.uaa.token.beans.entity.OAuth2TokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

/**
 * Token 对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {LocalDateTime.class, TokenUtil.class, TokenStyleEnum.class}
)
public interface OAuth2TokenConvert {

	@Mappings({
		@Mapping(target = "accessTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(param.accessTokenValidity()))"),
		@Mapping(target = "refreshTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(param.refreshTokenValidity()))"),
		@Mapping(target = "accessToken", expression = "java(TokenUtil.generateToken(TokenStyleEnum.ACCESS_TOKEN))"),
		@Mapping(target = "refreshToken", expression = "java(TokenUtil.generateToken(TokenStyleEnum.REFRESH_TOKEN))"),
		@Mapping(target = "attachedInfo", source = "attachedInfoMap")
	})
	OAuth2TokenEntity buildEntity(OAuth2TokenCreateParam param);

	OAuth2TokenCreateResult buildOAuth2TokenCreateResult(OAuth2TokenEntity entity);
}
