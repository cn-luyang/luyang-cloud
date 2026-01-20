package io.github.luyang.platform.uaa.code.beans.bo;

import java.util.Collections;
import java.util.Set;

/**
 * OAuth2 授权码创建参数
 *
 * @param clientId                  客户端 ID
 * @param userId                    用户 ID
 * @param scopes                    申请的权限范围
 * @param redirectUri               重定向 URI
 * @param nonce                     随机串
 * @param codeChallenge             PKCE 码
 * @param codeChallengeMethod       PKCE 计算方法
 * @param authorizationCodeValidity 授权码有效期 (秒)，可选
 */
public record OAuth2CodeCreateParam(
	String clientId,
	String userId,
	Set<String> scopes,
	String redirectUri,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod,
	Integer authorizationCodeValidity
) {

	public OAuth2CodeCreateParam {
		scopes = (scopes == null) ? Collections.emptySet() : Set.copyOf(scopes);
	}
}
