package io.github.luyang.platform.open.v1.client.service;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.base.enums.error.ClientError;
import io.github.luyang.platform.open.v1.client.controller.request.CreateClientRequest;
import io.github.luyang.platform.open.v1.client.controller.request.UpdateClientRequest;
import io.github.luyang.platform.open.v1.client.controller.response.CreateClientResponse;
import io.github.luyang.platform.open.v1.client.controller.response.GetClientResponse;
import io.github.luyang.platform.open.v1.client.convert.ClientConvert;
import io.github.luyang.platform.open.v1.client.repository.ClientRepository;
import io.github.luyang.platform.open.v1.client.repository.entity.ClientDO;
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
	 * @param createClientRequest 创建客户端请求体
	 * @return 创建客户端响应体
	 * @author yang.lu
	 */
	public CreateClientResponse createClient(CreateClientRequest createClientRequest) {

		// 检查客户端名称是否唯一
		boolean hasClientName = clientRepository.existsClientName(createClientRequest.getClientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		// 转换为实体并保存至数据库
		ClientDO clientDO = clientConvert.convertToClientDO(createClientRequest, passwordEncoder);
		clientRepository.save(clientDO);

		// 获取客户端 ID 和明文密钥
		String clientId = clientDO.getClientId();
		String clientSecret = clientDO.getClientSecretPlain();

		// 构造返回对象，包含 clientId 和明文密钥
		return CreateClientResponse.builder().clientId(clientId).clientSecretPlain(clientSecret).build();
	}

	/**
	 * 删除客户端
	 *
	 * @param clientId 客户端ID
	 * @author yang.lu
	 */
	public void deleteClient(String clientId) {
		boolean hasClientId = clientRepository.existsClientId(clientId);
		if (hasClientId) {
			clientRepository.removeByClientId(clientId);
		}
	}

	/**
	 * 更新客户端
	 *
	 * @param updateClientRequest 更新客户端请求体
	 * @author yang.lu
	 */
	public void updateClient(UpdateClientRequest updateClientRequest) {

		// 检查客户端ID是否存在
		ClientDO clientDO = clientRepository.findByClientId(updateClientRequest.getClientId());
		ClientError.INVALID_CLIENT.notNull(clientDO);

		// 客户端名称变更时校验唯一性
		String newClientName = updateClientRequest.getClientName();
		String oldClientName = clientDO.getClientId();
		if (StrUtil.isNotEmpty(newClientName) && !StrUtil.equals(oldClientName, newClientName)) {
			// 检查客户端名称是否唯一
			boolean hasClientName = clientRepository.existsClientName(newClientName);
			ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);
		}

		// 将 DTO 转换为实体并更新至数据库
		clientConvert.convertToEntity(updateClientRequest).updateById();
	}

	/**
	 * 获取客户端详情信息
	 *
	 * @param clientId 客户端 ID
	 * @return 客户端信息响应体
	 * @author yang.lu
	 */
	public GetClientResponse getClient(String clientId) {
		ClientDO clientDO = clientRepository.findByClientId(clientId);
		if (null == clientDO) {
			return new GetClientResponse();
		}

		return clientConvert.convertToGetClientRes(clientDO);
	}
}
