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

	/**
	 * 移除指定客户端和用户的所有 Token
	 *
	 * @param clientId 客户端 ID
	 * @param userId   用户 ID
	 * @author yang.lu
	 */
	public void removeByClientIdAndUserId(String clientId, String userId) {
		this.lambdaUpdate()
			.eq(TokenEntity::getClientId, clientId)
			.eq(TokenEntity::getUserId, userId)
			.remove();
	}
}
