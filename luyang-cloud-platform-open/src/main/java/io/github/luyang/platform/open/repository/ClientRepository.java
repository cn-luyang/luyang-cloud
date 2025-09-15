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

	/**
	 * 判断客户端名称是否已存在
	 *
	 * @param clientName 客户端名称
	 * @return true 表示存在，false 表示不存在
	 * @author yang.lu
	 */
	public boolean existsClientName(String clientName) {
		return this.lambdaQuery().eq(ClientEntity::getClientName, clientName).exists();
	}

	@Override
	public boolean save(ClientEntity entity) {
		return super.save(entity);
	}
}
