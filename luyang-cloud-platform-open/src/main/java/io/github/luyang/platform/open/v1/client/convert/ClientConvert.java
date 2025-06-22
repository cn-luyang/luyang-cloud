package io.github.luyang.platform.open.v1.client.convert;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import io.github.luyang.platform.open.v1.client.controller.request.CreateClientRequest;
import io.github.luyang.platform.open.v1.client.controller.request.UpdateClientRequest;
import io.github.luyang.platform.open.v1.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.v1.client.repository.entity.ClientDO;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 客户端相关实体转换
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface ClientConvert {

	default ClientDO convertToClientDO(CreateClientRequest createClientRequest, @Context PasswordEncoder passwordEncoder) {

		String clientId = "cli_" + RandomUtil.randomString(16);
		String clientSecret = IdUtil.simpleUUID();

		return new ClientDO()
			.setClientId(clientId)
			.setClientName(createClientRequest.getClientName())
			.setClientSecret(passwordEncoder.encode(clientSecret))
			.setClientSecretPlain(clientSecret)
			.setAccessTokenValidity(createClientRequest.getAccessTokenValidity())
			.setRefreshTokenValidity(createClientRequest.getRefreshTokenValidity())
			.setGrantTypes(ListUtil.toList(createClientRequest.getGrantTypes()))
			.setRedirectUris(ListUtil.toList(createClientRequest.getRedirectUris()))
			.setAutoApprove(createClientRequest.getAutoApprove())
			.setDescription(createClientRequest.getDescription());
	}

	default ClientDO convertToEntity(UpdateClientRequest updateClientRequest) {

		return new ClientDO()
			.setClientId(updateClientRequest.getClientId())
			.setClientName(updateClientRequest.getClientName())
			.setAccessTokenValidity(updateClientRequest.getAccessTokenValidity())
			.setRefreshTokenValidity(updateClientRequest.getRefreshTokenValidity())
			.setGrantTypes(ListUtil.toList(updateClientRequest.getGrantTypes()))
			.setRedirectUris(ListUtil.toList(updateClientRequest.getRedirectUris()))
			.setAutoApprove(updateClientRequest.getAutoApprove())
			.setDescription(updateClientRequest.getDescription());
	}

	GetClientResponse convertToGetClientRes(ClientDO clientDO);
}
