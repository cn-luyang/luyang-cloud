package io.github.luyang.platform.open.service;

import io.github.luyang.platform.open.beans.domain.ClientDomain;
import io.github.luyang.platform.open.beans.request.ClientCreateReq;

/**
 * 客户端业务服务接口
 *
 * @author yang.lu
 */
public interface ClientService {

	/**
	 * 创建新客户端
	 *
	 * @param clientCreateReq 客户端创建请求对象
	 * @return 客户端 ID
	 * @author yang.lu
	 */
	String create(ClientCreateReq clientCreateReq);

	/**
	 * 根据客户端 ID 查询客户端信息
	 *
	 * @param clientId 客户端ID
	 * @return 客户端 DTO 对象
	 * @author yang.lu
	 */
	ClientDomain get(String clientId);
}
