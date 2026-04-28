package io.github.luyang.platform.uaa.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa.beans.TokenDO;
import io.github.luyang.platform.uaa.common.enums.CacheKey;
import io.github.luyang.platform.uaa.mapper.TokenMapper;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class TokenRepository extends ServiceImpl<TokenMapper, TokenDO> {

	private final RedissonHelper redissonHelper;

	@Override
	public boolean save(TokenDO tokenDO) {

		boolean saveSuccess = super.save(tokenDO);
		if (saveSuccess) {
			String redisKey = CacheKey.ACCESS_TOKEN.of(tokenDO.getAccessToken());
			redissonHelper.setString(redisKey, tokenDO);
		}

		return saveSuccess;
	}

	public TokenDO findByAccessToken(String accessToken) {
		String redisKey = CacheKey.ACCESS_TOKEN.of(accessToken);
		TokenDO tokenDO = redissonHelper.getString(redisKey);
		if (null == tokenDO) {
			tokenDO = super.lambdaQuery().eq(TokenDO::getAccessToken, accessToken).one();
			if (null == tokenDO) {
				redissonHelper.setString(redisKey, tokenDO);
			}
		}
		return tokenDO;
	}
}
