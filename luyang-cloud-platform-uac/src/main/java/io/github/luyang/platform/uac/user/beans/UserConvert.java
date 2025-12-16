package io.github.luyang.platform.uac.user.beans;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uac.user.beans.body.UserCreateRequest;
import io.github.luyang.platform.uac.user.beans.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户 对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {IdUtil.class}
)
public interface UserConvert {

	@Mapping(target = "userId", expression = "java(\"u_\" + IdUtil.nanoId(32))")
	UserEntity buildEntity(UserCreateRequest userCreateRequest);

	UserDomain buildDomain(UserEntity entity);
}
