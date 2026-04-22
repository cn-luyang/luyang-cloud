package io.github.luyang.platform.uaa.code.beans;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa._common.enums.PkceMethodEnum;
import io.github.luyang.starter.base.enums.IBaseEnum;
import org.apache.commons.codec.digest.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Set;

/**
 * OAuth2 授权码 Domain
 *
 * @author yang.lu
 */
public record OAuth2CodeDomain(
	String code,
	String clientId,
	String userId,
	Set<String> scopes,
	String redirectUri,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod,
	LocalDateTime expiresTime,
	Boolean used,
	LocalDateTime usedTime
) {

	/**
	 * 验证 PKCE code_verifier
	 *
	 * @param codeVerifier 客户端提供的验证码
	 * @return true 验证通过， false 验证失败
	 * @author yang.lu
	 */
	public boolean validatePkce(String codeVerifier) {

		// 获取 PKCE 编码方式枚举
		PkceMethodEnum pkceMethodEnum = IBaseEnum.getByCode(PkceMethodEnum.class, codeChallengeMethod);
		return switch (pkceMethodEnum) {
			case PLAIN -> StrUtil.equals(codeChallenge, codeVerifier);
			case S256 -> {
				// code_verifier 作为 ASCII 字符串处理
				byte[] verifierBytes = codeVerifier.getBytes(StandardCharsets.US_ASCII);
				// 计算 SHA256 哈希
				byte[] hash = DigestUtils.getSha256Digest().digest(verifierBytes);
				// Base64 URL 编码
				String computedChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
				// 比较是否一样
				yield StrUtil.equals(computedChallenge, codeChallenge);
			}
		};
	}
}
