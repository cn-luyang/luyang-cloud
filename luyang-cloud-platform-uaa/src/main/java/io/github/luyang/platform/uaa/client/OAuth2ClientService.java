package io.github.luyang.platform.uaa.client;

import io.github.luyang.platform.uaa._common.enums.error.OAuth2ClientError;
import io.github.luyang.platform.uaa.client.beans.OAuth2ClientConverter;
import io.github.luyang.platform.uaa.client.beans.OAuth2ClientDomain;
import io.github.luyang.platform.uaa.client.beans.body.OAuth2ClientCreateRequest;
import io.github.luyang.platform.uaa.client.beans.body.OAuth2ClientCreateResponse;
import io.github.luyang.platform.uaa.client.beans.entity.OAuth2ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * OAuth2 客户端业务服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2ClientService {

	private final OAuth2ClientRepository clientRepository;
	private final OAuth2ClientConverter clientConverter;

	/**
	 * 创建 OAuth2 客户端
	 *
	 * @param request 创建请求参数
	 * @return 客户端创建响应
	 * @author yang.lu
	 */
	public OAuth2ClientCreateResponse create(OAuth2ClientCreateRequest request) {

		boolean hasClientName = clientRepository.clientNameUnique(request.clientName());
		OAuth2ClientError.EXISTS_CLIENT_NAME.isFalse(hasClientName);

		OAuth2ClientEntity entity = clientConverter.buildEntity(request);

		boolean hasSuccess = clientRepository.save(entity);
		OAuth2ClientError.CLIENT_SAVE_FAILED.isTrue(hasSuccess);

		return OAuth2ClientCreateResponse.build(entity.getClientId());
	}

	/**
	 * 根据客户端 ID 获取客户端领域模型
	 *
	 * @param clientId 客户端 ID
	 * @return 客户端领域模型
	 * @author yang.lu
	 */
	public OAuth2ClientDomain getDomainByClientId(String clientId) {
		OAuth2ClientEntity entity = clientRepository.getById(clientId);
		return this.clientConverter.buildDomain(entity);
	}
}
