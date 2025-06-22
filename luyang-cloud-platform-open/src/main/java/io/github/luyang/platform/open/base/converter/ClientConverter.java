package io.github.luyang.platform.open.base.converter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import io.github.luyang.platform.open.client.controller.request.ClientCreateRequest;
import io.github.luyang.platform.open.client.controller.request.ClientUpdateRequest;
import io.github.luyang.platform.open.client.controller.response.ClientResponse;
import io.github.luyang.platform.open.client.domain.ClientBO;
import io.github.luyang.platform.open.client.domain.ClientCommand;
import io.github.luyang.platform.open.client.repository.model.ClientDO;
import jakarta.validation.Valid;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 客户端对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		RandomUtil.class, IdUtil.class
	})
public interface ClientConverter {

	/**
	 * ClientCreateRequest 转换为 ClientCommand
	 *
	 * @param request 创建客户端请求
	 * @return 客户端命令对象
	 * @author yang.lu
	 */
	ClientCommand toCommand(@Valid ClientCreateRequest request);

	/**
	 * ClientUpdateRequest 转换为 ClientCommand
	 *
	 * @param request 更新客户端请求
	 * @return 客户端命令对象
	 * @author yang.lu
	 */
	ClientCommand toCommand(@Valid ClientUpdateRequest request);

	/**
	 * ClientCommand 转换为 ClientDO
	 *
	 * @param command  客户端命令对象
	 * @param clientDO 客户端DO对象
	 * @return 客户端DO对象
	 * @author yang.lu
	 */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "clientId", ignore = true)
	@Mapping(target = "clientSecret", ignore = true)
	@Mapping(target = "clientSecretPlain", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ClientDO toDO(ClientCommand command, @MappingTarget ClientDO clientDO);

	/**
	 * ClientCommand 转换为 ClientDO
	 * <ul>
	 *     <li>自动生成 clientId（"cli_随机16位字符串"）</li>
	 *     <li>自动生成 clientSecretPlain（明文密钥，UUID）</li>
	 *     <li>使用 passwordEncoder 对明文密钥加密，存储到 clientSecret</li>
	 * </ul>
	 *
	 * @param command 客户端命令对象
	 * @return 客户端DO对象
	 * @author yang.lu
	 */
	@Mapping(target = "clientId", expression = "java(\"cli_\" + RandomUtil.randomString(16))")
	@Mapping(target = "clientSecretPlain", expression = "java(IdUtil.simpleUUID())")
	@Mapping(target = "clientSecret", expression = "java(passwordEncoder.encode(clientSecretPlain))")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ClientDO toDO(ClientCommand command, @Context PasswordEncoder passwordEncoder);

	/**
	 * ClientDO 转换为 ClientBO
	 *
	 * @param clientDO 客户端DO对象
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientBO toBO(ClientDO clientDO);

	/**
	 * ClientBO 转换为 ClientResponse
	 *
	 * @param clientBO 客户端业务对象
	 * @return 客户端响应对象
	 * @author yang.lu
	 */
	ClientResponse toResponse(ClientBO clientBO);
}
