package io.github.luyang.platform.open.client.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.client.repository.entity.ClientDO;
import org.springframework.stereotype.Repository;

/**
 * 客户端相关 Repository
 *
 * @author yang.lu
 */
@Repository
public class ClientRepository extends ServiceImpl<ClientMapper, ClientDO> {

	public ClientDO findByClientId(String clientId) {
		return this.baseMapper.selectOne(ClientDO::getClientId, clientId);
	}

	public boolean existsByClientName(String clientName) {
		return this.baseMapper.exists(ClientDO::getClientName, clientName);
	}

	public boolean existsByClientId(String clientId) {
		return this.baseMapper.exists(ClientDO::getClientId, clientId);
	}

	public void removeByClientId(String clientId) {
		this.baseMapper.delete(ClientDO::getClientId, clientId);
	}
}
