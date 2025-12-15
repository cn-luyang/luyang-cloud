package io.github.luyang.platform.uaa.client.beans;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uaa.client.beans.body.OAuth2ClientCreateRequest;
import io.github.luyang.platform.uaa.client.beans.entity.OAuth2ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * OAuth2 客户端数据转换器
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class})
public interface OAuth2ClientConverter {

	@Mapping(target = "clientId", expression = "java(\"cli_\" + IdUtil.nanoId(16))")
	@Mapping(target = "clientSecret", expression = "java(IdUtil.simpleUUID())")
	OAuth2ClientEntity buildEntity(OAuth2ClientCreateRequest oAuth2ClientCreateRequest);

	OAuth2ClientDomain buildDomain(OAuth2ClientEntity entity);
}
