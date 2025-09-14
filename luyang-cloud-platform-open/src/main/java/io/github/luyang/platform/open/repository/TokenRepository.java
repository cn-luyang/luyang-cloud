package io.github.luyang.platform.open.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.beans.entity.TokenEntity;
import io.github.luyang.platform.open.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Token 数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class TokenRepository extends ServiceImpl<TokenMapper, TokenEntity> {

	public void removeByClientIdAndUserId(String clientId, String userId) {
		this.lambdaUpdate()
			.eq(TokenEntity::getClientId, clientId)
			.eq(TokenEntity::getUserId, userId)
			.remove();
	}
}
