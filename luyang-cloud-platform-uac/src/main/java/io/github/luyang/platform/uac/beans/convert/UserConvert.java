package io.github.luyang.platform.uac.beans.convert;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uac.beans.domain.UserDomain;
import io.github.luyang.platform.uac.beans.entity.UserEntity;
import io.github.luyang.platform.uac.beans.request.UserCreateReq;
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
