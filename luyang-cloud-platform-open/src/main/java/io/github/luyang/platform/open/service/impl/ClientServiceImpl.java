package io.github.luyang.platform.open.service.impl;

import io.github.luyang.platform.open.beans.convert.ClientConverter;
import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.entity.ClientEntity;
import io.github.luyang.platform.open.beans.enums.error.ClientError;
import io.github.luyang.platform.open.beans.request.ClientCreateReq;
import io.github.luyang.platform.open.repository.ClientRepository;
import io.github.luyang.platform.open.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 客户端业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	private final ClientRepository clientRepository;
	private final ClientConverter clientConverter;

	@Override
	public String create(ClientCreateReq clientCreateReq) {

		boolean hasClientName = clientRepository.existsClientName(clientCreateReq.clientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		ClientEntity entity = clientConverter.buildEntity(clientCreateReq);

		clientRepository.save(entity);

		return entity.getClientId();
	}

	@Override
	public ClientDomain get(String clientId) {
		ClientEntity entity = clientRepository.getById(clientId);
		return this.clientConverter.buildDomain(entity);
	}
}
