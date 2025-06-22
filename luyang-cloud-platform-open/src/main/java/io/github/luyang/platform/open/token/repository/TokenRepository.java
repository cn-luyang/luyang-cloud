package io.github.luyang.platform.open.token.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
public class TokenRepository extends ServiceImpl<TokenMapper, TokenDO> {

	public TokenDO find(TokenRenewalQuery tokenRenewalQuery) {
		return this.lambdaQuery()
			.eq(TokenDO::getClientId, tokenRenewalQuery.getClientId())
			.eq(TokenDO::getUserId, tokenRenewalQuery.getUserId())
			.gt(TokenDO::getAccessTokenExpiresTime, tokenRenewalQuery.getNowTime())
			.one();
	}

	public void modify(TokenRenewalOps tokenRenewalOps) {
		this.lambdaUpdate()
			.eq(TokenDO::getId, tokenRenewalOps.getId())
			.set(TokenDO::getAccessTokenExpiresTime, tokenRenewalOps.getAccessTokenExpiresTime())
			.set(TokenDO::getRefreshTokenExpiresTime, tokenRenewalOps.getRefreshTokenExpiresTime())
			.update();
	}
}
