package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.convert.ClientConvert;
import io.github.luyang.platform.open.model.ClientName;
import io.github.luyang.platform.open.model.dto.CreateClientDTO;
import io.github.luyang.platform.open.model.entity.ClientEntity;
import io.github.luyang.platform.open.model.vo.CreateClientVO;
import io.github.luyang.platform.open.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientConvert clientConvert;

	public CreateClientVO create(CreateClientDTO createClientDTO) {

		ClientName clientName = ClientName.build(createClientDTO.getClientName());
		clientName.checkUnique(() -> clientRepository.unique(clientName));

		ClientEntity entity = clientConvert.dtoToEntity(createClientDTO);
		clientRepository.save(entity);

		String clientId = entity.getClientId();
		String clientSecret = entity.getClientId();

		return CreateClientVO
			.builder()
			.clientId(clientId)
			.clientSecretPlain(clientSecret)
			.build();
	}
}
