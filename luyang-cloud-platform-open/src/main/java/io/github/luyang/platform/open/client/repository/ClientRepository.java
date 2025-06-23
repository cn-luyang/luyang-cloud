package io.github.luyang.platform.open.client.repository;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.client.domain.ClientQuery;
import io.github.luyang.platform.open.client.repository.model.ClientDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户端数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientDO> {

	private final ClientMapper mapper;

	/**
	 * 查询客户端ID是否存在
	 *
	 * @param clientId 客户端ID
	 * @return 存在返回 true；否则返回 false
	 * @author yang.lu
	 */
	public boolean existsClientId(String clientId) {
		return this.mapper.exists(ClientDO::getClientId, clientId);
	}

	/**
	 * 查询客户端名称是否存在
	 *
	 * @param clientName 客户端名称
	 * @return 存在返回 true；否则返回 false
	 * @author yang.lu
	 */
	public boolean existsClientName(String clientName) {
		return this.mapper.exists(ClientDO::getClientName, clientName);
	}

	/**
	 * 根据客户端ID查询客户端
	 *
	 * @param clientId 客户端ID
	 * @return 客户端信息，如果不存在则返回 null
	 * @author yang.lu
	 */
	public ClientDO findByClientId(String clientId) {
		return this.lambdaQuery()
			.eq(ClientDO::getClientId, clientId)
			.one();
	}

	/**
	 * 根据客户端ID删除客户端
	 *
	 * @param clientId 客户端ID。
	 * @return 删除成功返回 true；否则返回 false
	 * @author yang.lu
	 */
	public boolean removeByClientId(String clientId) {
		return this.mapper.delete(ClientDO::getClientId, clientId);
	}

	/**
	 * 查询所有客户端
	 *
	 * @return 客户端信息列表，按创建时间降序排列
	 * @author yang.lu
	 */
	public List<ClientDO> findAll() {
		return this.lambdaQuery().orderByDesc(ClientDO::getCreatedTime).list();
	}


	/**
	 * 分页查询客户端
	 *
	 * @return 客户端信息列表，按创建时间降序排列
	 * @author yang.lu
	 */
	public IPage<ClientDO> findPage(ClientQuery query) {
		return this.lambdaQuery()
			.like(StrUtil.isNotBlank(query.clientId()), ClientDO::getClientId, query.clientId())
			.like(StrUtil.isNotBlank(query.clientName()), ClientDO::getClientName, query.clientName())
			.orderByDesc(ClientDO::getCreatedTime)
			.page(new Page<>(query.pageNum(), query.pageSize()));
	}
}
