package io.github.luyang.platform.open.base.converter;

import io.github.luyang.platform.open.base.enums.TokenStyle;
import io.github.luyang.platform.open.base.util.TokenUtil;
import io.github.luyang.platform.open.token.domain.TokenCommand;
import io.github.luyang.platform.open.token.domain.TokenDomain;
import io.github.luyang.platform.open.token.repository.model.TokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(
	componentModel = "spring",
	imports = {LocalDateTime.class, TokenUtil.class, TokenStyle.class}
)
public interface TokenConvert {

	@Mappings({
		@Mapping(target = "accessTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(command.accessTokenValidity()))"),
		@Mapping(target = "refreshTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(command.refreshTokenValidity()))"),
		@Mapping(target = "accessToken", expression = "java(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN))"),
		@Mapping(target = "refreshToken", expression = "java(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN))")
	})
	TokenDO toDO(TokenCommand command);

	TokenDomain toDomain(TokenDO tokenDO);
}
