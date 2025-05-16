package io.github.luyang.platform.open.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.model.ClientName;
import io.github.luyang.platform.open.model.entity.ClientEntity;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
public class ClientRepository extends ServiceImpl<ClientMapper, ClientEntity> {

	public boolean unique(ClientName clientName) {
		return this.baseMapper.exists(ClientEntity::getClientName, clientName.value());
	}
}
