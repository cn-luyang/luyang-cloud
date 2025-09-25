package io.github.luyang.platform.open.client;

import io.github.luyang.platform.open.client.body.ClientCreateRequest;
import io.github.luyang.platform.open.client.body.ClientCreateResponse;
import io.github.luyang.platform.open.common.enums.error.ClientError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 客户端服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientConverter clientConverter;

	/**
	 * 创建客户端
	 *
	 * @param clientCreateRequest 客户端创建请求
	 * @return 客户端创建响应
	 * @author yang.lu
	 */
	public ClientCreateResponse create(ClientCreateRequest clientCreateRequest) {

		boolean hasClientName = clientRepository.existsClientName(clientCreateRequest.clientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		ClientEntity entity = clientConverter.buildEntity(clientCreateRequest);

		clientRepository.save(entity);

		return ClientCreateResponse.build(entity.getClientId());
	}

	public ClientDomain getDomain(String clientId) {
		ClientEntity entity = clientRepository.getById(clientId);
		return this.clientConverter.buildDomain(entity);
	}
}
