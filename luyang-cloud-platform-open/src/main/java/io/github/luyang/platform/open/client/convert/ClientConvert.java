package io.github.luyang.platform.open.client.convert;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import io.github.luyang.platform.open.client.controller.request.CreateClientReq;
import io.github.luyang.platform.open.client.controller.request.UpdateClientReq;
import io.github.luyang.platform.open.client.controller.response.GetClientRes;
import io.github.luyang.platform.open.client.repository.entity.ClientDO;
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

	default ClientDO convertToEntity(CreateClientReq createClientReq, @Context PasswordEncoder passwordEncoder) {

		String clientId = "cli_" + RandomUtil.randomString(16);
		String clientSecret = IdUtil.simpleUUID();

		return new ClientDO()
			.setClientId(clientId)
			.setClientName(createClientReq.getClientName())
			.setClientSecret(passwordEncoder.encode(clientSecret))
			.setClientSecretPlain(clientSecret)
			.setAccessTokenValidity(createClientReq.getAccessTokenValidity())
			.setRefreshTokenValidity(createClientReq.getRefreshTokenValidity())
			.setGrantTypes(ListUtil.toList(createClientReq.getGrantTypes()))
			.setRedirectUris(ListUtil.toList(createClientReq.getRedirectUris()))
			.setAutoApprove(createClientReq.getAutoApprove())
			.setDescription(createClientReq.getDescription());
	}

	default ClientDO convertToEntity(UpdateClientReq updateClientReq) {

		return new ClientDO()
			.setClientId(updateClientReq.getClientId())
			.setClientName(updateClientReq.getClientName())
			.setAccessTokenValidity(updateClientReq.getAccessTokenValidity())
			.setRefreshTokenValidity(updateClientReq.getRefreshTokenValidity())
			.setGrantTypes(ListUtil.toList(updateClientReq.getGrantTypes()))
			.setRedirectUris(ListUtil.toList(updateClientReq.getRedirectUris()))
			.setAutoApprove(updateClientReq.getAutoApprove())
			.setDescription(updateClientReq.getDescription());
	}

	GetClientRes convertToGetClientRes(ClientDO clientDO);
}
