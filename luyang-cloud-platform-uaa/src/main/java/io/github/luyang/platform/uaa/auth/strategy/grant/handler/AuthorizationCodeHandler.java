package io.github.luyang.platform.uaa.auth.strategy.grant.handler;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa._common.enums.error.OAuth2CodeError;
import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenRequest;
import io.github.luyang.platform.uaa.auth.beans.body.ApplyTokenResponse;
import io.github.luyang.platform.uaa.auth.strategy.grant.OAuth2GrantHandler;
import io.github.luyang.platform.uaa.code.OAuth2CodeService;
import io.github.luyang.platform.uaa.code.beans.OAuth2CodeDomain;
import io.github.luyang.platform.uaa.token.OAuth2TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 授权码模式处理器
 *
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class AuthorizationCodeHandler implements OAuth2GrantHandler {

	private final OAuth2CodeService codeService;
	private final OAuth2TokenService tokenService;

	@Override
	public ApplyTokenResponse handle(ApplyTokenRequest request) {

		OAuth2CodeDomain codeDomain = codeService.getDomainByCode(request.code());
		// 未获取到授权码信息
		OAuth2CodeError.CODE_NOT_FOUND.notNull(codeDomain);
		// 客户端不匹配
		OAuth2CodeError.CODE_CLIENT_MISMATCH.isTrue(StrUtil.equals(codeDomain.clientId(), request.clientId()));
		// 重定向 URI 不匹配
		OAuth2CodeError.CODE_REDIRECT_URI_MISMATCH.isTrue(StrUtil.equals(codeDomain.redirectUri(), request.redirectUri()));
		// 授权码被使用
		OAuth2CodeError.CODE_ALREADY_USED.isFalse(codeDomain.used());
		// 授权码已过期
		OAuth2CodeError.CODE_EXPIRED.isFalse(codeDomain.expiresTime().isBefore(LocalDateTime.now()));
		// 验证 codeVerifier
		boolean validateSuccess = codeDomain.validatePkce(request.codeVerifier());
		OAuth2CodeError.CODE_VERIFIER_MISMATCH.isTrue(validateSuccess);
		// TODO: 生成TOKEN
		tokenService.issueToken();
		// TODO: 作废授权码
		return null;
	}
}
