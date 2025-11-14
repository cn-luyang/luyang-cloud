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
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientConverter clientConverter;

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
