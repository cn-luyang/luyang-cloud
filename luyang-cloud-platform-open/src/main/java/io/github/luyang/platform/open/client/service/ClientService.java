package io.github.luyang.platform.open.client.service;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.open.base.enums.error.ClientError;
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

		// 检查客户端名称是否唯一
		boolean hasClientName = clientRepository.existsByClientName(createClientReq.getClientName());
		ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		// 转换为实体并保存至数据库
		ClientDO clientDO = clientConvert.convertToEntity(createClientReq, passwordEncoder);
		clientRepository.save(clientDO);

		// 获取客户端 ID 和明文密钥
		String clientId = clientDO.getClientId();
		String clientSecret = clientDO.getClientSecretPlain();

		// 构造返回对象，包含 clientId 和明文密钥
		return CreateClientRes.builder().clientId(clientId).clientSecretPlain(clientSecret).build();
	}

	/**
	 * 删除客户端
	 *
	 * @param clientId 客户端ID
	 * @author yang.lu
	 */
	public void deleteClient(String clientId) {
		boolean hasClientId = clientRepository.existsByClientId(clientId);
		if (hasClientId) {
			clientRepository.removeByClientId(clientId);
		}
	}

	/**
	 * 更新客户端
	 *
	 * @param updateClientReq 更新客户端请求体
	 * @author yang.lu
	 */
	public void updateClient(UpdateClientReq updateClientReq) {

		// 检查客户端ID是否存在
		ClientDO clientDO = clientRepository.findByClientId(updateClientReq.getClientId());
		ClientError.INVALID_CLIENT.notNull(clientDO);

		// 客户端名称变更时校验唯一性
		String newClientName = updateClientReq.getClientName();
		String oldClientName = clientDO.getClientId();
		if (!StrUtil.equals(oldClientName, newClientName)) {
			// 检查客户端名称是否唯一
			boolean hasClientName = clientRepository.existsByClientName(newClientName);
			ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);
		}

		// 将 DTO 转换为实体并更新至数据库
		clientConvert.convertToEntity(updateClientReq).updateById();
	}

	/**
	 * 获取客户端信息
	 *
	 * @param clientId 客户端 ID
	 * @return 客户端信息响应体
	 * @author yang.lu
	 */
	public GetClientRes getClient(String clientId) {
		ClientDO clientDO = clientRepository.findByClientId(clientId);
		if (null == clientDO) {
			return new GetClientRes();
		}

		return clientConvert.convertToGetClientRes(clientDO);
	}
}
