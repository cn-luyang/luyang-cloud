package io.github.luyang.platform.uac.beans.convert;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.platform.uac.beans.UserDO;
import io.github.luyang.platform.uac.beans.payload.CreateUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	@Mapping(target = "password", source = "password", qualifiedByName = "encode")
	UserDO buildEntity(CreateUserDTO createUserDTO);

	@Named("encode")
	default String encodePassword(String rawPassword) {
		return SpringUtil.getBean(PasswordEncoder.class).encode(rawPassword);
	}
}
