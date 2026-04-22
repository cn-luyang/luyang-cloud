package io.github.luyang.platform.uaa.token.beans;

import io.github.luyang.platform.uaa._common.util.TokenUtil;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

/**
 * Token 对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {LocalDateTime.class, TokenUtil.class, TokenUtil.TokenStyleEnum.class}
)
public interface OAuth2TokenConvert {

}
