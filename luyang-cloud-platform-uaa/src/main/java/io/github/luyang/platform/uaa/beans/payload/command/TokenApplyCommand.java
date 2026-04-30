package io.github.luyang.platform.uaa.beans.payload.command;

/**
 * Token 申请请求
 *
 * @param grantType    授权类型
 * @param code         授权码(ST临时票据)
 * @param redirectUri  重定向 URI,必须与授权请求中的值一致
 * @param codeVerifier PKCE，原始随机密码
 * @param refreshToken 刷新令牌,换取AccessToken
 * @author yang.lu
 */
public record TokenApplyCommand(
	String grantType,
	String code,
	String redirectUri,
	String codeVerifier,
	String refreshToken
) {
}
