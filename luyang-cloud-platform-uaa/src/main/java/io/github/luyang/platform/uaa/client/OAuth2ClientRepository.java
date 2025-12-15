package io.github.luyang.platform.uaa.client;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa.client.beans.entity.OAuth2ClientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * OAuth2 客户端数据访问层
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class OAuth2ClientRepository extends ServiceImpl<OAuth2ClientMapper, OAuth2ClientEntity> {

	private final OAuth2ClientMapper clientMapper;

	public boolean checkClientNameUnique(String clientName) {
		return this.lambdaQuery().eq(OAuth2ClientEntity::getClientName, clientName).exists();
	}
}
