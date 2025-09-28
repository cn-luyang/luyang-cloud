package io.github.luyang.platform.uaa.token.beans;

import io.github.luyang.platform.uaa.common.enums.TokenStyleEnum;
import io.github.luyang.platform.uaa.common.util.TokenUtil;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateParam;
import io.github.luyang.platform.uaa.token.beans.bo.TokenCreateResult;
import io.github.luyang.platform.uaa.token.beans.entity.TokenEntity;
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
public interface TokenConvert {

	@Mappings({
		@Mapping(target = "accessTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(param.accessTokenValidity()))"),
		@Mapping(target = "refreshTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(param.refreshTokenValidity()))"),
		@Mapping(target = "accessToken", expression = "java(TokenUtil.generateToken(TokenStyleEnum.ACCESS_TOKEN))"),
		@Mapping(target = "refreshToken", expression = "java(TokenUtil.generateToken(TokenStyleEnum.REFRESH_TOKEN))"),
		@Mapping(target = "attachedInfo", source = "attachedInfoMap")
	})
	TokenEntity buildEntity(TokenCreateParam param);

	TokenCreateResult buildTokenCreateResult(TokenEntity entity);
}
