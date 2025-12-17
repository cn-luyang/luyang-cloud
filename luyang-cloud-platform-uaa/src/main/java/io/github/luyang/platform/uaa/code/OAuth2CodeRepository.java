package io.github.luyang.platform.uaa.code;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa._common.constant.OAuth2Constant;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.Duration;
import java.util.Optional;

/**
 * OAuth2 授权码数据访问层
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class OAuth2CodeRepository extends ServiceImpl<OAuth2CodeMapper, OAuth2CodeEntity> {

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
}
