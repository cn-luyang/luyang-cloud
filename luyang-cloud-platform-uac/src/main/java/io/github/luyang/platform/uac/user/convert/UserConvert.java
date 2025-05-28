package io.github.luyang.platform.uac.user.convert;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uac.user.controller.request.CreateUserRequest;
import io.github.luyang.platform.uac.user.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	componentModel = "spring",
	imports = {IdUtil.class}
)
public interface UserConvert {

	@Mapping(target = "userId", expression = "java(IdUtil.simpleUUID())")
	UserEntity createUserRequestToEntity(CreateUserRequest createUserRequest);
}
