package io.github.luyang.platform.uaa.code.beans;

import io.github.luyang.platform.uaa._common.enums.infra.RedisKey;
import io.github.luyang.platform.uaa.code.beans.bo.OAuth2CodeCreateParam;
import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OAuth2CodeConvert {

	default OAuth2CodeEntity buildEntity(OAuth2CodeCreateParam param) {
		OAuth2CodeEntity entity = new OAuth2CodeEntity();

		entity.setClientId(param.clientId());
		entity.setUserId(param.userId());
		Set<String> set = param.scopes();
		if (set != null) {
			entity.setScopes(set);
		}
		entity.setRedirectUri(param.redirectUri());
		entity.setNonce(param.nonce());
		entity.setCodeChallenge(param.codeChallenge());
		entity.setCodeChallengeMethod(param.codeChallengeMethod());

		LocalDateTime now = LocalDateTime.now();
		entity.setIssuedTime(now);
		entity.setExpiresTime(now.plusSeconds(param.authorizationCodeValidity() != null ? param.authorizationCodeValidity() : RedisKey.AUTHORIZATION_CODE.getTtl().toSeconds()));
		return entity;
	}

	OAuth2CodeDomain buildDomain(OAuth2CodeEntity entity);
}
