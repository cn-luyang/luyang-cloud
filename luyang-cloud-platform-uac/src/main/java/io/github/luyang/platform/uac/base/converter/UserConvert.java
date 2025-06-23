package io.github.luyang.platform.uac.base.converter;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uac.user.controller.request.UserCreateRequest;
import io.github.luyang.platform.uac.user.controller.response.UserResponse;
import io.github.luyang.platform.uac.user.domain.UserCommand;
import io.github.luyang.platform.uac.user.domain.UserDomain;
import io.github.luyang.platform.uac.user.repository.model.UserDO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 用户相关实体转换
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {IdUtil.class}
)
public interface UserConvert {

	@Mapping(target = "userId", expression = "java(IdUtil.simpleUUID())")
	@Mapping(target = "password", expression = "java(passwordEncoder.encode(command.password()))")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserDO toDO(UserCommand command, @Context PasswordEncoder passwordEncoder);

	UserDomain toDomain(UserDO userDO);

	UserCommand toCommand(UserCreateRequest createUserRequest);

	UserResponse toResponse(UserDomain userDomain);
}
