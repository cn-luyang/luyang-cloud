package io.github.luyang.platform.uaa.open;

import io.github.luyang.platform.uaa._common.enums.ResponseTypeEnum;
import io.github.luyang.platform.uaa._common.enums.error.ClientError;
import io.github.luyang.platform.uaa.client.ClientService;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.open.beans.body.AuthorizeRequest;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OpenService {

	private final ClientService clientService;

	public ResponseEntity<?> authorize(AuthorizeRequest authorizeRequest) {

		// 验证response_type必须是code
		IBaseEnum.executeIfCodeNotMatches(ResponseTypeEnum.CODE, authorizeRequest.responseType(), () -> {
			throw new RuntimeException("response_type，响应类型必须是“code”");
		});

		// 获取客户端信息
		ClientDomain clientDomain = clientService.getDomain(authorizeRequest.clientId());
		ClientError.INVALID_CLIENT.notNull(clientDomain);

		// 验证重定向URI
		boolean validRedirectUri = clientDomain.isValidRedirectUri(authorizeRequest.redirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		if (authorizeRequest.codeChallenge() != null && !isValidCodeChallengeMethod(authorizeRequest.codeChallengeMethod())) {
			return buildErrorResponse("invalid_request","Invalid code challenge method", redirectUri, state);
		}

		// 验证PKCE参数
		// 检查用户是否已认证
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			// 重定向到登录页面
			return buildAuthenticationRedirect(request, clientId, redirectUri,
				scope, state, codeChallenge, codeChallengeMethod);
		}

		// 生成授权码
		// 构建重定向URI with authorization code
		String redirectUrl = buildAuthorizationRedirectUri(redirectUri,authorizationCode.getCode(), state);

		return ResponseEntity.status(HttpStatus.FOUND)
			.header("Location", redirectUrl)
			.build();
	}

	private boolean isValidCodeChallengeMethod(String method) {
		return method == null || "S256".equals(method) || "plain".equals(method);
	}
}
