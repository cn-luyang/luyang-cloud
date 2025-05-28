package io.github.luyang.platform.open.client.service;

import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.base.valueobject.ClientName;
import io.github.luyang.platform.open.client.controller.request.CreateClientRequest;
import io.github.luyang.platform.open.client.controller.request.UpdateClientRequest;
import io.github.luyang.platform.open.client.controller.response.CreateClientResponse;
import io.github.luyang.platform.open.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.client.convert.ClientConvert;
import io.github.luyang.platform.open.client.repository.ClientRepository;
import io.github.luyang.platform.open.client.repository.entity.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientConvert clientConvert;
	private final PasswordEncoder passwordEncoder;

	public CreateClientResponse createClient(CreateClientRequest createClientRequest) {

		// 构建并校验客户端名称唯一性
		ClientName clientName = ClientName.build(createClientRequest.getClientName());
		clientName.checkUnique(() -> clientRepository.unique(clientName));

		// 将 DTO 转换为实体并保存至数据库
		ClientEntity entity = clientConvert.toEntity(createClientRequest, passwordEncoder);
		clientRepository.save(entity);

		// 获取客户端 ID 和明文密钥
		String clientId = entity.getClientId();
		String clientSecret = entity.getClientSecretPlain();

		// 构造返回对象，包含 clientId 和明文密钥
		return CreateClientResponse.builder().clientId(clientId).clientSecretPlain(clientSecret).build();
	}

	public void deleteClient(String clientId) {
		clientRepository.remove(ClientId.build(clientId));
	}

	public void updateClient(UpdateClientRequest updateClientRequest) {

		// 构建 ClientId 并查找客户端实体
		ClientId clientId = ClientId.build(updateClientRequest.getClientId());
		ClientEntity clientEntity = clientRepository.find(clientId);

		// 如果客户端不存在，则抛出异常
		ClientError.CLIENT_INVALID.notNull(clientEntity);

		// 构建 ClientName 并在名称变更时校验唯一性
		ClientName clientName = ClientName.build(updateClientRequest.getClientName());
		clientName.checkUniqueIfChanged(clientEntity.getClientName(), () -> clientRepository.unique(clientName));

		// 将 DTO 转换为实体并更新至数据库
		clientConvert.toEntity(updateClientRequest, clientEntity);
		clientEntity.updateById();
	}

	public GetClientResponse getClient(String clientId) {
		ClientEntity clientEntity = clientRepository.find(ClientId.build(clientId));
		if (null == clientEntity) {
			return null;
		}

		return clientConvert.toGetClientResponse(clientEntity);
	}
}
