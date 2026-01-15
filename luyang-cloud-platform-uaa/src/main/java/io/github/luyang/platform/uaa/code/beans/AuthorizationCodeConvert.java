package io.github.luyang.platform.uaa.code.beans;

import io.github.luyang.platform.uaa.code.beans.bo.AuthorizationCodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.entity.AuthorizationCodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorizationCodeConvert {

	AuthorizationCodeEntity buildEntity(AuthorizationCodeCreateParam param);

	AuthorizationCodeDomain buildDomain(AuthorizationCodeEntity entity);
}
