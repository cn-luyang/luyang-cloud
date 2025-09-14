package io.github.luyang.platform.open.beans.convert;

import io.github.luyang.platform.open.beans.command.UserTokenCreateCommand;
import io.github.luyang.platform.open.beans.domain.TokenDomain;
import io.github.luyang.platform.open.beans.entity.TokenEntity;
import io.github.luyang.platform.open.enums.TokenStyle;
import io.github.luyang.platform.open.util.TokenUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {LocalDateTime.class, TokenUtil.class, TokenStyle.class}
)
public interface TokenConvert {

	/**
	 * UserTokenCreateCommand 转换为 TokenEntity
	 * <ul>
	 *     <li>accessTokenExpiresTime 当前时间加上客户端访问令牌时间</li>
	 *     <li>refreshTokenExpiresTime 当前时间加上客户端刷新令牌时间</li>
	 *     <li>accessToken {@link TokenUtil#generateToken(TokenStyle)}}</li>
	 *     <li>refreshToken {@link TokenUtil#generateToken(TokenStyle)}}</li>
	 * </ul>
	 *
	 * @param command 用户token创建命令对象
	 * @return Token 实体
	 * @author yang.lu
	 */
	@Mappings({
		@Mapping(target = "accessTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(command.accessTokenValidity()))"),
		@Mapping(target = "refreshTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(command.refreshTokenValidity()))"),
		@Mapping(target = "accessToken", expression = "java(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN))"),
		@Mapping(target = "refreshToken", expression = "java(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN))"),
		@Mapping(target = "attachedInfo", source = "attachedInfoMap")
	})
	TokenEntity buildEntity(UserTokenCreateCommand command);

	TokenDomain buildDomain(TokenEntity entity);
}
