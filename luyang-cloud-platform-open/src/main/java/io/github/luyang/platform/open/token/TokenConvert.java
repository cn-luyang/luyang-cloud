package io.github.luyang.platform.open.token;

import io.github.luyang.platform.open.common.enums.TokenStyle;
import io.github.luyang.platform.open.common.util.TokenUtil;
import io.github.luyang.platform.open.token.bo.TokenCreateParam;
import io.github.luyang.platform.open.token.bo.TokenCreateResult;
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
	imports = {LocalDateTime.class, TokenUtil.class, TokenStyle.class}
)
public interface TokenConvert {

	/**
	 * 根据用户 Token 创建参数构建 Token 实体
	 *
	 * @param param 用户 Token 创建参数
	 * @return 构建完成的 Token 实体对象
	 * @author yang.lu
	 */
	@Mappings({
		@Mapping(target = "accessTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(param.accessTokenValidity()))"),
		@Mapping(target = "refreshTokenExpiresTime", expression = "java(LocalDateTime.now().plusSeconds(param.refreshTokenValidity()))"),
		@Mapping(target = "accessToken", expression = "java(TokenUtil.generateToken(TokenStyle.ACCESS_TOKEN))"),
		@Mapping(target = "refreshToken", expression = "java(TokenUtil.generateToken(TokenStyle.REFRESH_TOKEN))"),
		@Mapping(target = "attachedInfo", source = "attachedInfoMap")
	})
	TokenEntity buildEntity(TokenCreateParam param);

	TokenCreateResult buildTokenCreateResult(TokenEntity entity);
}
