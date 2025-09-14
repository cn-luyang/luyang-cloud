package io.github.luyang.platform.open.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.beans.entity.ClientEntity;
import io.github.luyang.platform.open.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 客户端数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientEntity> {

	private final ClientMapper clientMapper;

	public boolean existsClientName(String clientName) {
		return this.lambdaQuery().eq(ClientEntity::getClientName, clientName).exists();
	}

	@Override
	public boolean save(ClientEntity entity) {
		return super.save(entity);
	}
}
