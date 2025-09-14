package io.github.luyang.platform.open.service.impl;

import io.github.luyang.platform.open.beans.convert.ClientConverter;
import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.entity.ClientEntity;
import io.github.luyang.platform.open.beans.request.ClientCreateRequest;
import io.github.luyang.platform.open.enums.error.ClientError;
import io.github.luyang.platform.open.repository.ClientRepository;
import io.github.luyang.platform.open.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 客户端业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;
	private final ClientConverter clientConverter;

	@Override
	public String create(ClientCreateRequest request) {

		boolean hasClientName = clientRepository.existsClientName(request.clientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		ClientEntity entity = clientConverter.buildEntity(request);

		clientRepository.save(entity);

		return entity.getClientId();
	}

	@Override
	public ClientDomain get(String clientId) {
		ClientEntity entity = clientRepository.getById(clientId);
		return this.clientConverter.buildDomain(entity);
	}
}
