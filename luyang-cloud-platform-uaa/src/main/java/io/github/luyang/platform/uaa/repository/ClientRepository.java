package io.github.luyang.platform.uaa.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa.beans.ClientDO;
import io.github.luyang.platform.uaa.common.enums.CacheKey;
import io.github.luyang.platform.uaa.mapper.ClientMapper;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.Duration;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientDO> {

	private final static Logger logger = LoggerFactory.getLogger(ClientRepository.class);

	private final RedissonHelper redissonHelper;

	public boolean clientNameUnique(String clientName) {
		return this.lambdaQuery()
			.eq(ClientDO::getClientName, clientName)
			.exists();
	}

	@Override
	public boolean save(ClientDO clientDO) {
		boolean saveSuccess = super.save(clientDO);
		if (saveSuccess) {
			String clientId = clientDO.getClientId();
			Duration timeout = CacheKey.CLIENT_INFO.getTimeout();
			redissonHelper.setString(CacheKey.CLIENT_INFO.of(clientId), clientDO, timeout);
		}
		return saveSuccess;
	}

	@Override
	public ClientDO getById(Serializable clientId) {
		ClientDO clientDO = redissonHelper.getString(CacheKey.CLIENT_INFO.of((String) clientId));
		if (null == clientDO) {
			clientDO = super.getById(clientId);
			if (null != clientDO) {
				Duration timeout = CacheKey.CLIENT_INFO.getTimeout();
				redissonHelper.setString(CacheKey.CLIENT_INFO.of((String) clientId), clientDO, timeout);
			}
		}

		return clientDO;
	}
}
