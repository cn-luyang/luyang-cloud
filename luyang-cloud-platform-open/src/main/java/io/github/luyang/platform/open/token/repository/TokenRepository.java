package io.github.luyang.platform.open.token.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.token.repository.model.TokenDO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Token 数据仓库
 *
 * @author yang.lu
 */
@Repository
public class TokenRepository extends ServiceImpl<TokenMapper, TokenDO> {

	public void removeByClientIdAndUserId(String clientId, String userId) {
		this.lambdaUpdate()
			.eq(TokenDO::getClientId, clientId)
			.eq(TokenDO::getUserId, userId)
			.remove();
	}

	public TokenDO findValidToken(String clientId, String userId) {
		return this.lambdaQuery()
			.eq(TokenDO::getClientId, clientId)
			.eq(TokenDO::getUserId, userId)
			.and(wrapper -> wrapper
				.gt(TokenDO::getAccessTokenExpiresTime, LocalDateTime.now())
				.or()
				.gt(TokenDO::getRefreshTokenExpiresTime, LocalDateTime.now()))
			.one();
	}

	public TokenDO findByAccessToken(String accessToken) {
		return this.lambdaQuery()
			.eq(TokenDO::getAccessToken, accessToken)
			.one();
	}
}
