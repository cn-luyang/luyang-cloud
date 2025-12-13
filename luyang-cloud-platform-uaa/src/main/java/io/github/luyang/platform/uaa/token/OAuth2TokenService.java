package io.github.luyang.platform.uaa.token;

import io.github.luyang.platform.uaa.token.beans.OAuth2TokenConvert;
import io.github.luyang.platform.uaa.token.beans.bo.OAuth2TokenCreateParam;
import io.github.luyang.platform.uaa.token.beans.bo.OAuth2TokenCreateResult;
import io.github.luyang.platform.uaa.token.beans.entity.OAuth2TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2TokenService {

	private final OAuth2TokenRepository oAuth2TokenRepository;
	private final OAuth2TokenConvert oAuth2TokenConvert;

	public OAuth2TokenCreateResult create(OAuth2TokenCreateParam param) {

		// 移除同一客户端和用户下的旧 Token
		this.oAuth2TokenRepository.removeByClientIdAndUserId(param.clientId(), param.userId());

		// 构建并持久化 Token 实体
		OAuth2TokenEntity entity = oAuth2TokenConvert.buildEntity(param);
		oAuth2TokenRepository.save(entity);

		return oAuth2TokenConvert.buildOAuth2TokenCreateResult(entity);
	}
}
