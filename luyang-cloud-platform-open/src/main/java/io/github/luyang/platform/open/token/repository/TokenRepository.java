package io.github.luyang.platform.open.token.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalOps;
import io.github.luyang.platform.open.token.repository.model.TokenRenewalQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author yang.lu
 */
@Repository
public class TokenRepository extends ServiceImpl<TokenMapper, TokenDO> {

	public Optional<TokenDO> find(TokenRenewalQuery tokenRenewalQuery) {
		if (null == tokenRenewalQuery) {
			return Optional.empty();
		}

		return this.lambdaQuery()
			.eq(TokenDO::getClientId, tokenRenewalQuery.getClientId())
			.eq(TokenDO::getUserId, tokenRenewalQuery.getUserId())
			.le(TokenDO::getAccessTokenExpiresTime, tokenRenewalQuery.getNowTime())
			.oneOpt();
	}

	public boolean operation(TokenRenewalOps tokenRenewalOps) {
		return true;
	}
}
