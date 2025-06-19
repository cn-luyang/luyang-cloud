package io.github.luyang.platform.open.auth.convert;

import io.github.luyang.platform.open.auth.repository.entity.LoginRequestEntity;
import io.github.luyang.platform.open.base.config.properties.AuthProperties;
import io.github.luyang.starter.web.util.SpringUtil;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface LoginRequestConvert {

	default LoginRequestEntity toEntity(String authorizeRequestId, String userId) {
		LoginRequestEntity loginRequestEntity = new LoginRequestEntity();
		loginRequestEntity.setAuthorizeRequestId(Long.valueOf(authorizeRequestId));
		loginRequestEntity.setUserId(userId);
		AuthProperties authProperties = SpringUtil.getBean(AuthProperties.class);
		loginRequestEntity.setExpireTime(LocalDateTime.now().plusSeconds(authProperties.getLoginIdValidity()));
		return loginRequestEntity;
	}
}
