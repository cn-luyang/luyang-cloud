package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.request.ClientCreateRequest;

/**
 * 客户端业务服务接口
 *
 * @author yang.lu
 */
public interface ClientService {

	/**
	 * 创建客户端
	 *
	 * @param request 客户端创建请求对象
	 * @return 客户端ID
	 * @author yang.lu
	 */
	String create(ClientCreateRequest request);

	/**
	 * 获取客户端详情
	 *
	 * @param clientId 客户端ID
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientDomain get(String clientId);
}
