package io.github.luyang.platform.uaa.service.impl;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.platform.uaa.beans.ClientDO;
import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.convert.ClientConvert;
import io.github.luyang.platform.uaa.beans.payload.command.ClientCreateCommand;
import io.github.luyang.platform.uaa.beans.payload.vo.CreateClientVO;
import io.github.luyang.platform.uaa.common.enums.ErrorCode;
import io.github.luyang.platform.uaa.repository.ClientRepository;
import io.github.luyang.platform.uaa.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

	private final static Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

//	private final PasswordEncoder passwordEncoder;
	private final ClientRepository clientRepository;
	private final ClientConvert clientConvert;

	@Override
	public CreateClientVO create(ClientCreateCommand command) {

		boolean hasClientName = clientRepository.clientNameUnique(command.clientName());
		ErrorCode.CLIENT_NAME_EXISTS.isFalse(hasClientName);

		String plainText = IdUtil.fastSimpleUUID();
//		String cipherText = passwordEncoder.encode(plainText);
		String cipherText ="123";
		ClientDO clientDO = clientConvert.buildClientDO(command, cipherText);

		clientRepository.save(clientDO);

		return CreateClientVO.build(clientDO.getClientId(), plainText);
	}

	@Override
	public ClientDomain getDomain(String clientId) {
		ClientDO clientDO = clientRepository.getById(clientId);
		return clientConvert.buildClientDomain(clientDO);
	}
}
