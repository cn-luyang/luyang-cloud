package io.github.luyang.platform.uaa.beans.dto;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa.common.enums.PkceMethod;
import io.github.luyang.starter.base.enums.IBaseEnum;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public record TempTicketDTO(
	String userId,
	String clientId,
	String redirectUri,
	String responseType,
	String scope,
	String state,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod
) implements Serializable {
	@Serial
	private static final long serialVersionUID = 7478925607322833955L;

	/**
	 * 验证 PKCE code_verifier
	 *
	 * @param codeVerifier 客户端提供的验证码
	 * @return true 验证通过， false 验证失败
	 * @author yang.lu
	 */
	public boolean validatePkce(String codeVerifier) {

		// 获取 PKCE 编码方式枚举
		PkceMethod pkceMethod = IBaseEnum.getByCode(PkceMethod.class, codeChallengeMethod);
		return switch (pkceMethod) {
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
