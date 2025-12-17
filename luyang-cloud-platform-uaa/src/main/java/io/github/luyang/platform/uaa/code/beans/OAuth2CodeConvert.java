package io.github.luyang.platform.uaa.code.beans;

import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OAuth2CodeConvert {

	OAuth2CodeEntity buildEntity(OAuth2CodeCreateParam param);

	OAuth2CodeDomain buildDomain(OAuth2CodeEntity entity);
}
