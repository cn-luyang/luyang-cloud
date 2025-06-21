package io.github.luyang.platform.open.client.service;

import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.base.valueobject.ClientName;
import io.github.luyang.platform.open.client.controller.request.CreateClientReq;
import io.github.luyang.platform.open.client.controller.request.UpdateClientReq;
import io.github.luyang.platform.open.client.controller.response.CreateClientRes;
import io.github.luyang.platform.open.client.controller.response.GetClientRes;
import io.github.luyang.platform.open.client.convert.ClientConvert;
import io.github.luyang.platform.open.client.repository.ClientRepository;
import io.github.luyang.platform.open.client.repository.entity.ClientDO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 客户端服务类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientConvert clientConvert;
	private final PasswordEncoder passwordEncoder;
	private final ClientRepository clientRepository;

	/**
	 * 创建客户端
	 *
	 * @param createClientReq 创建客户端请求体
	 * @return 创建客户端响应体
	 * @author yang.lu
	 */
	public CreateClientRes createClient(CreateClientReq createClientReq) {

		// 构建并校验客户端名称唯一性
		ClientName clientName = ClientName.build(createClientReq.getClientName());
		clientName.checkUnique(() -> clientRepository.unique(clientName));

		// 将 DTO 转换为实体并保存至数据库
		ClientDO clientDO = clientConvert.toEntity(createClientReq, passwordEncoder);
		clientRepository.save(clientDO);

		// 获取客户端 ID 和明文密钥
		String clientId = clientDO.getClientId();
		String clientSecret = clientDO.getClientSecretPlain();

		// 构造返回对象，包含 clientId 和明文密钥
		return CreateClientRes.builder().clientId(clientId).clientSecretPlain(clientSecret).build();
	}

	public void deleteClient(ClientId clientId) {
		clientRepository.remove(clientId);
	}

	/**
	 * 更新客户端
	 *
	 * @param updateClientReq 更新客户端请求体
	 * @author yang.lu
	 */
	public void updateClient(UpdateClientReq updateClientReq) {

		// 构建 ClientId 并查找客户端实体
		ClientId clientId = ClientId.build(updateClientReq.getClientId());
		ClientDO clientDO = clientRepository.find(clientId);

		// 如果客户端不存在，则抛出异常
		ClientError.INVALID_CLIENT.notNull(clientDO);

		// 构建 ClientName 并在名称变更时校验唯一性
		ClientName clientName = ClientName.build(updateClientReq.getClientName());
		clientName.checkUniqueIfChanged(clientDO.getClientName(), () -> clientRepository.unique(clientName));

		// 将 DTO 转换为实体并更新至数据库
		clientConvert.toEntity(updateClientReq, clientDO);
		clientDO.updateById();
	}

	/**
	 * 获取客户端信息
	 *
	 * @param clientId 客户端 ID
	 * @return 客户端信息响应体
	 * @author yang.lu
	 */
	public GetClientRes getClient(ClientId clientId) {
		ClientDO clientDO = clientRepository.find(clientId);
		if (null == clientDO) {
			return null;
		}

		return clientConvert.toGetClientResponse(clientDO);
	}

	public boolean existClient(ClientId clientId) {
		return null != getClient(clientId);
	}
}
