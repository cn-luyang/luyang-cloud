package io.github.luyang.platform.uaa.open;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa._common.enums.CodeChallengeMethodEnum;
import io.github.luyang.platform.uaa._common.enums.ResponseTypeEnum;
import io.github.luyang.platform.uaa._common.enums.error.ClientError;
import io.github.luyang.platform.uaa.client.ClientService;
import io.github.luyang.platform.uaa.client.beans.ClientDomain;
import io.github.luyang.platform.uaa.open.beans.body.AuthorizeRequest;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OpenService {

	private final ClientService clientService;

	public void authorize(AuthorizeRequest authorizeRequest) {

		// response_type 必须为 code
		boolean validResponseType = ResponseTypeEnum.CODE.getCode().equals(authorizeRequest.responseType());
		ClientError.RESPONSE_TYPE_MUST_BE_CODE.isTrue(validResponseType);

		// 校验客户端是否存在
		ClientDomain clientDomain = clientService.getDomain(authorizeRequest.clientId());
		ClientError.INVALID_CLIENT.notNull(clientDomain);

		// 校验 redirect_uri 是否在允许的回调地址中
		boolean validRedirectUri = clientDomain.isValidRedirectUri(authorizeRequest.redirectUri());
		ClientError.INVALID_REDIRECT_URI.isTrue(validRedirectUri);

		// 验证scopes
		// boolean validScope = clientDomain.isValidScopes(authorizeRequest.scopes());
		// ClientError.INVALID_SCOPE.isTrue(validScope);

		// 校验 code_challenge_method 是否有效
		CodeChallengeMethodEnum codeChallengeMethodEnum = IBaseEnum.getByCode(
			CodeChallengeMethodEnum.class, authorizeRequest.codeChallengeMethod()
		);
		ClientError.INVALID_CODE_CHALLENGE_METHOD.notNull(codeChallengeMethodEnum);

		// 校验 code_challenge 是否存在
		boolean validCodeChallenge = StrUtil.isNotBlank(authorizeRequest.codeChallenge());
		ClientError.MISSING_CODE_CHALLENGE.isTrue(validCodeChallenge);

		/*
			基于Cookie检查用户是否已认证
				已经认证
					保存当前请求参数及code，下发code携带state
				未认证
					跳转到登录界面拼接当前请求参数
		 */
	}
}
