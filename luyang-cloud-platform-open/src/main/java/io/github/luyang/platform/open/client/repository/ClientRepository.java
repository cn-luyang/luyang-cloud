package io.github.luyang.platform.open.client.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.base.valueobject.ClientId;
import io.github.luyang.platform.open.base.valueobject.ClientName;
import io.github.luyang.platform.open.client.repository.entity.ClientEntity;
import org.springframework.stereotype.Repository;

/**
 * 客户端相关 Repository
 *
 * @author yang.lu
 */
@Repository
public class ClientRepository extends ServiceImpl<ClientMapper, ClientEntity> {

	public boolean unique(ClientName clientName) {
		return this.baseMapper.exists(ClientEntity::getClientName, clientName.value());
	}

	public ClientEntity find(ClientId clientId) {
		return this.baseMapper.selectOne(ClientEntity::getClientId, clientId.value());
	}

	public boolean remove(ClientId clientId) {
		return this.baseMapper.delete(ClientEntity::getClientId, clientId.value());
	}
}
