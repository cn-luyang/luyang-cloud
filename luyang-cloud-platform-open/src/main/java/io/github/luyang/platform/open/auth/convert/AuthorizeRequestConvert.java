package io.github.luyang.platform.open.auth.convert;

import io.github.luyang.platform.open.auth.controller.request.AuthorizeRequest;
import io.github.luyang.platform.open.auth.repository.entity.AuthorizeRequestEntity;
import io.github.luyang.platform.open.base.config.properties.AuthProperties;
import io.github.luyang.starter.web.util.SpringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface AuthorizeRequestConvert {

	@Mapping(target = "expireTime", qualifiedByName = "authIdExpireTime")
	AuthorizeRequestEntity toEntity(AuthorizeRequest authorizeRequest);

	@Named("authIdExpireTime")
	default LocalDateTime authIdExpireTime() {
		AuthProperties authProperties = SpringUtil.getBean(AuthProperties.class);
		return LocalDateTime.now().plusSeconds(authProperties.getAuthIdValidity());
	}
}
