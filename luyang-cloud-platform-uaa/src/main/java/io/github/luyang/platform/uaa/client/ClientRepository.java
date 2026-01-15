package io.github.luyang.platform.uaa.client;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa._common.constant.AuthConstant;
import io.github.luyang.platform.uaa.client.beans.entity.ClientEntity;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.Duration;

/**
 * 客户端数据访问层
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class ClientRepository extends ServiceImpl<ClientMapper, ClientEntity> {

	private final RedissonHelper redissonHelper;

	public boolean clientNameUnique(String clientName) {
		return this.lambdaQuery().eq(ClientEntity::getClientName, clientName).exists();
	}

	@Override
	public boolean save(ClientEntity entity) {

		boolean hasSuccess = super.save(entity);
		if (hasSuccess) {
			// 添加 Redis缓存
			cacheClient(entity);
		}

		return hasSuccess;
	}

	@Override
	public ClientEntity getById(Serializable clientId) {

		String redisKey = AuthConstant.buildClientRedisKey(clientId.toString());
		ClientEntity cachedEntity = redissonHelper.getString(redisKey);
		if (null != cachedEntity) {
			return cachedEntity;
		}

		ClientEntity entity = super.getById(clientId);
		if (null != entity) {
			cacheClient(entity);
		}

		return entity;
	}

	private void cacheClient(ClientEntity entity) {
		// 添加 Redis缓存，有效期8小时
		String redisKey = AuthConstant.buildClientRedisKey(entity.getClientId());
		redissonHelper.setString(redisKey, entity, Duration.ofHours(8));
	}
}
