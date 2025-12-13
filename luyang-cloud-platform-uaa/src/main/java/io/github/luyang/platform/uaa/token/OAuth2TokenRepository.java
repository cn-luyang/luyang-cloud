package io.github.luyang.platform.uaa.token;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa.token.beans.entity.OAuth2TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class OAuth2TokenRepository extends ServiceImpl<OAuth2TokenMapper, OAuth2TokenEntity> {

	/**
	 * @param clientId 客户端 ID
	 * @param userId   用户 ID
	 * @author yang.lu
	 */
	public void removeByClientIdAndUserId(String clientId, String userId) {
		this.lambdaUpdate()
			.eq(OAuth2TokenEntity::getClientId, clientId)
			.eq(OAuth2TokenEntity::getUserId, userId)
			.remove();
	}
}
