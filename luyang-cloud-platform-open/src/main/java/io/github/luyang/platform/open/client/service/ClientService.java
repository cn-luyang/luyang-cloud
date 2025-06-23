package io.github.luyang.platform.open.client.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.luyang.platform.open.client.domain.ClientCommand;
import io.github.luyang.platform.open.client.domain.ClientDomain;
import io.github.luyang.platform.open.client.domain.ClientQuery;

import java.util.List;

/**
 * 客户端业务服务接口
 *
 * @author yang.lu
 */
public interface ClientService {

	/**
	 * 创建客户端
	 *
	 * @param command 客户端创建命令对象
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientDomain create(ClientCommand command);

	/**
	 * 删除客户端
	 *
	 * @param clientId 客户端ID
	 * @author yang.lu
	 */
	void delete(String clientId);

	/**
	 * 更新客户端
	 *
	 * @param command 客户端更新命令对象
	 * @author yang.lu
	 */
	void update(ClientCommand command);

	/**
	 * 获取客户端详情
	 *
	 * @param clientId 客户端ID
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientDomain get(String clientId);

	/**
	 * 获取所有客户端
	 *
	 * @return 所有客户端业务对象
	 * @author yang.lu
	 */
	List<ClientDomain> list();

	/**
	 * 分页查询客户端
	 *
	 * @param query 客户端查询条件对象
	 * @return 客户端业务对象的分页结果
	 * @author yang.lu
	 */
	IPage<ClientDomain> page(ClientQuery query);


	/**
	 * 客户端验证
	 *
	 * @param command 客户端验证命令对象
	 * @return 客户端业务对象
	 * @author yang.lu
	 */
	ClientDomain validate(ClientCommand command);
}
