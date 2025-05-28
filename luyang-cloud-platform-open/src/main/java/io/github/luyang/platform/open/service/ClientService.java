package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.convert.ClientConvert;
import io.github.luyang.platform.open.enums.ClientError;
import io.github.luyang.platform.open.model.ClientId;
import io.github.luyang.platform.open.model.ClientName;
import io.github.luyang.platform.open.model.dto.CreateClientDTO;
import io.github.luyang.platform.open.model.dto.UpdateClientDTO;
import io.github.luyang.platform.open.model.entity.ClientEntity;
import io.github.luyang.platform.open.model.vo.CreateClientVO;
import io.github.luyang.platform.open.model.vo.GetClientVO;
import io.github.luyang.platform.open.repository.ClientRepository;
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

	public CreateClientVO createClient(CreateClientDTO createClientDTO) {

		// 构建并校验客户端名称唯一性
		ClientName clientName = ClientName.build(createClientDTO.getClientName());
		clientName.checkUnique(() -> clientRepository.unique(clientName));

		// 将 DTO 转换为实体并保存至数据库
		ClientEntity entity = clientConvert.createDtoToEntity(createClientDTO, passwordEncoder);
		clientRepository.save(entity);

		// 获取客户端 ID 和明文密钥
		String clientId = entity.getClientId();
		String clientSecret = entity.getClientSecretPlain();

		// 构造返回对象，包含 clientId 和明文密钥
		return CreateClientVO.builder().clientId(clientId).clientSecretPlain(clientSecret).build();
	}

	public void deleteClient(String clientId) {
		clientRepository.remove(ClientId.build(clientId));
	}

	public void updateClient(UpdateClientDTO updateClientDTO) {

		// 构建 ClientId 并查找客户端实体
		ClientId clientId = ClientId.build(updateClientDTO.getClientId());
		ClientEntity clientEntity = clientRepository.find(clientId);

		// 如果客户端不存在，则抛出异常
		ClientError.CLIENT_INVALID.notNull(clientEntity);

		// 构建 ClientName 并在名称变更时校验唯一性
		ClientName clientName = ClientName.build(updateClientDTO.getClientName());
		clientName.checkUniqueIfChanged(clientEntity.getClientName(), () -> clientRepository.unique(clientName));

		// 将 DTO 转换为实体并更新至数据库
		clientConvert.updateDtoToEntity(updateClientDTO, clientEntity);
		clientEntity.updateById();
	}

	public GetClientVO getClient(String clientId) {
		ClientEntity clientEntity = clientRepository.find(ClientId.build(clientId));
		if (null == clientEntity) {
			return null;
		}

		return clientConvert.entityToGetVO(clientEntity);
	}
}
