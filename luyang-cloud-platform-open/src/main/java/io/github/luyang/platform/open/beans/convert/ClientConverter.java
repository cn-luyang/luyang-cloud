package io.github.luyang.platform.open.beans.convert;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.entity.ClientEntity;
import io.github.luyang.platform.open.beans.request.ClientCreateReq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 客户端对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {IdUtil.class})
public interface ClientConverter {

	/**
	 * 将客户端创建请求转换为实体对象
	 *
	 * @param req 客户端创建请求对象
	 * @return 转换后的客户端实体对象
	 * @author yang.lu
	 */
	@Mapping(target = "clientId", expression = "java(\"cli_\" + IdUtil.nanoId(16))")
	@Mapping(target = "clientSecret", expression = "java(IdUtil.simpleUUID())")
	ClientEntity buildEntity(ClientCreateReq req);

	/**
	 * 将客户端实体对象转换为 Domain
	 *
	 * @param entity 客户端实体对象
	 * @return 转换后的客户端 DTO
	 * @author yang.lu
	 */
	ClientDomain buildDomain(ClientEntity entity);
}
