package io.github.luyang.platform.open.client.service;

import io.github.luyang.platform.open.client.domain.ClientBO;
import io.github.luyang.platform.open.client.domain.ClientCommand;

import java.util.List;

/**
 * 客户端业务服务接口
 *
 * @author yang.lu
 */
public interface ClientService {

	/**
	 * 创建新客户端
	 *
	 * @param command 客户端创建命令对象
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientBO createClient(ClientCommand command);

	/**
	 * 根据客户端ID删除
	 *
	 * @param clientId 客户端ID
	 * @author yang.lu
	 */
	void deleteClient(String clientId);

	/**
	 * 更新客户端信息
	 *
	 * @param command 客户端更新命令对象
	 * @author yang.lu
	 */
	void updateClient(ClientCommand command);

	/**
	 * 获取所有客户端列表
	 *
	 * @return 所有客户端业务对象
	 * @author yang.lu
	 */
	List<ClientBO> getAllClients();
}
