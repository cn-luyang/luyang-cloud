package io.github.luyang.platform.uaa.client.beans;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uaa.client.beans.body.ClientCreateRequest;
import io.github.luyang.platform.uaa.client.beans.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 客户端数据转换器
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring", imports = {IdUtil.class})
public interface ClientConverter {

	@Mapping(target = "clientId", expression = "java(\"cli_\" + IdUtil.nanoId(16))")
	@Mapping(target = "clientSecret", expression = "java(IdUtil.simpleUUID())")
	ClientEntity buildEntity(ClientCreateRequest clientCreateRequest);

	ClientDomain buildDomain(ClientEntity entity);
}
