package io.github.luyang.platform.open.beans.convert;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.entity.ClientEntity;
import io.github.luyang.platform.open.beans.request.ClientCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {IdUtil.class})
public interface ClientConverter {

	/**
	 * ClientCommand 转换为 ClientDO
	 * <ul>
	 *     <li>自动生成 clientId（"cli_随机16位字符串"）</li>
	 *     <li>自动生成 clientSecret（明文密钥，UUID）</li>
	 * </ul>
	 *
	 * @param clientCreateRequest 客户端创建请求
	 * @return 客户端实体
	 * @author yang.lu
	 */
	@Mapping(target = "clientId", expression = "java(\"cli_\" + IdUtil.nanoId(16))")
	@Mapping(target = "clientSecret", expression = "java(IdUtil.simpleUUID())")
	ClientEntity buildEntity(ClientCreateRequest clientCreateRequest);

	/**
	 * ClientEntity 转换为 ClientDomain
	 *
	 * @param clientEntity 客户端实体
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientDomain buildDomain(ClientEntity clientEntity);
}
