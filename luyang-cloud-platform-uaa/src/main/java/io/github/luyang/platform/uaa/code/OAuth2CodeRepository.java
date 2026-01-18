package io.github.luyang.platform.uaa.code;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa._common.constant.OAuth2Constant;
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
			// 添加 Redis缓存，有效期3分钟
			String redisKey = OAuth2Constant.buildAuthorizationCodeRedisKey(entity.getCode());
			redissonHelper.setString(redisKey, entity, Duration.ofMinutes(3));
		}

		return hasSuccess;
	}

	@Override
	public OAuth2CodeEntity getById(Serializable code) {
		String redisKey = OAuth2Constant.buildAuthorizationCodeRedisKey(code.toString());
		OAuth2CodeEntity entity = redissonHelper.getString(redisKey);
		return Optional.ofNullable(entity).orElseGet(() -> super.getById(code));
	}

	public void consumedCode(String code) {
		boolean hasSuccess = this.lambdaUpdate()
			.set(OAuth2CodeEntity::getUsed, true)
			.set(OAuth2CodeEntity::getUsedAt, LocalDateTime.now())
			.eq(OAuth2CodeEntity::getCode, code)
			.update();
		if (hasSuccess) {
			String redisKey = OAuth2Constant.buildAuthorizationCodeRedisKey(code);
			try {
				redissonHelper.remove(redisKey);
			} catch (Exception e) {
				logger.error("Redis缓存删除授权码异常: {}", code, e);
			}
		}
	}
}
