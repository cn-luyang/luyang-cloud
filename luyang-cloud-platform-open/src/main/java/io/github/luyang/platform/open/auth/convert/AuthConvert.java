package io.github.luyang.platform.open.auth.convert;

import io.github.luyang.platform.open.auth.controller.request.AuthorizeRequest;
import io.github.luyang.platform.open.auth.repository.entity.AuthorizeRequestEntity;
import org.mapstruct.Mapper;

/**
 * 客户端相关实体转换
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface AuthConvert {


	AuthorizeRequestEntity toAuthorizeRequestEntity(AuthorizeRequest authorizeRequest);
}
