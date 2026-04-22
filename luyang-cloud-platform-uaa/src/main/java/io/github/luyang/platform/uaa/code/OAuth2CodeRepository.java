package io.github.luyang.platform.uaa.code;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa._common.enums.infra.RedisKey;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 授权码数据访问层
 *
 * @author yang.lu
 */

@Repository
@RequiredArgsConstructor
public class OAuth2CodeRepository extends ServiceImpl<OAuth2CodeMapper, OAuth2CodeEntity> {

	private static final Logger logger = LoggerFactory.getLogger(OAuth2CodeRepository.class);

	private final RedissonHelper redissonHelper;

	@Override
	public boolean save(OAuth2CodeEntity entity) {

		boolean hasSuccess = super.save(entity);
		if (hasSuccess) {
			String key = RedisKey.AUTHORIZATION_CODE.buildKey(entity.getCode());
			Duration ttl = Duration.between(LocalDateTime.now(), entity.getExpiresTime());
			redissonHelper.setString(key, entity, ttl);
		}

		return hasSuccess;
	}

	@Override
	public OAuth2CodeEntity getById(Serializable code) {
		String key = RedisKey.AUTHORIZATION_CODE.buildKey(code);
		OAuth2CodeEntity entity = redissonHelper.getString(key);
		return Optional.ofNullable(entity).orElseGet(() -> super.getById(code));
	}

	/**
	 * 消费授权码（标记为已使用并清理缓存）
	 */
	public void consumed(String code) {
		boolean hasSuccess = this.lambdaUpdate()
			.set(OAuth2CodeEntity::getUsed, true)
			.set(OAuth2CodeEntity::getUsedTime, LocalDateTime.now())
			.eq(OAuth2CodeEntity::getCode, code)
			.eq(OAuth2CodeEntity::getUsed, false)
			.update();

		if (hasSuccess) {
			String key = RedisKey.AUTHORIZATION_CODE.buildKey(code);
			try {
				redissonHelper.remove(key);
				logger.debug("授权码已消费并清理缓存: {}", code);
			} catch (Exception e) {
				logger.error("Redis缓存删除授权码异常 (Key: {}): {}", key, e.getMessage());
			}
		}
	}
}
