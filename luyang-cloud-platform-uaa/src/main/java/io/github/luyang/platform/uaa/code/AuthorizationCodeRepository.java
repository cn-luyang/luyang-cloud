package io.github.luyang.platform.uaa.code;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa._common.constant.AuthConstant;
import io.github.luyang.platform.uaa.code.beans.entity.AuthorizationCodeEntity;
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
public class AuthorizationCodeRepository extends ServiceImpl<AuthorizationCodeMapper, AuthorizationCodeEntity> {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationCodeRepository.class);

	private final RedissonHelper redissonHelper;

	@Override
	public boolean save(AuthorizationCodeEntity entity) {

		boolean hasSuccess = super.save(entity);
		if (hasSuccess) {
			// 添加 Redis缓存，有效期3分钟
			String redisKey = AuthConstant.buildAuthorizationCodeRedisKey(entity.getCode());
			redissonHelper.setString(redisKey, entity, Duration.ofMinutes(3));
		}

		return hasSuccess;
	}

	@Override
	public AuthorizationCodeEntity getById(Serializable code) {
		String redisKey = AuthConstant.buildAuthorizationCodeRedisKey(code.toString());
		AuthorizationCodeEntity entity = redissonHelper.getString(redisKey);
		return Optional.ofNullable(entity).orElseGet(() -> super.getById(code));
	}

	public void consumedCode(String code) {
		boolean hasSuccess = this.lambdaUpdate()
			.set(AuthorizationCodeEntity::getUsed, true)
			.set(AuthorizationCodeEntity::getUsedTime, LocalDateTime.now())
			.eq(AuthorizationCodeEntity::getCode, code)
			.update();
		if (hasSuccess) {
			String redisKey = AuthConstant.buildAuthorizationCodeRedisKey(code);
			try {
				redissonHelper.remove(redisKey);
			} catch (Exception e) {
				logger.error("Redis缓存删除授权码异常: {}", code, e);
			}
		}
	}
}
