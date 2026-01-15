package io.github.luyang.platform.uaa.client;

import io.github.luyang.platform.uaa._common.enums.error.ClientError;
import io.github.luyang.platform.uaa.client.beans.ClientConverter;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.client.beans.body.ClientCreateRequest;
import io.github.luyang.platform.uaa.client.beans.body.ClientCreateResponse;
import io.github.luyang.platform.uaa.client.beans.entity.ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 客户端业务服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientConverter clientConverter;

	/**
	 * 创建  客户端
	 *
	 * @param request 创建请求参数
	 * @return 客户端创建响应
	 * @author yang.lu
	 */
	public ClientCreateResponse create(ClientCreateRequest request) {

		boolean hasClientName = clientRepository.clientNameUnique(request.clientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		ClientEntity entity = clientConverter.buildEntity(request);

		boolean hasSuccess = clientRepository.save(entity);
		ClientError.CLIENT_SAVE_FAILED.isTrue(hasSuccess);

		return ClientCreateResponse.build(entity.getClientId());
	}

	/**
	 * 根据客户端 ID 获取客户端领域模型
	 *
	 * @param clientId 客户端 ID
	 * @return 客户端领域模型
	 * @author yang.lu
	 */
	public ClientDomain getDomainByClientId(String clientId) {
		ClientEntity entity = clientRepository.getById(clientId);
		return this.clientConverter.buildDomain(entity);
	}
}
