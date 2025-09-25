package io.github.luyang.platform.uac.common.convert;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uac.controller.request.UserCreateReq;
import io.github.luyang.platform.uac.mapper.entity.UserEntity;
import io.github.luyang.platform.uac.service.domain.UserDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {IdUtil.class}
)
public interface UserConvert {

	@Mapping(target = "userId", expression = "java(\"u_\" + IdUtil.nanoId(32))")
	UserEntity buildEntity(UserCreateReq userCreateReq);

	UserDomain buildDomain(UserEntity entity);
}
