package io.github.luyang.platform.uaa.service.strategy.granter.handler;

import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa.beans.dto.TempTicketDTO;
import io.github.luyang.platform.uaa.beans.payload.command.TokenApplyCommand;
import io.github.luyang.platform.uaa.common.enums.CacheKey;
import io.github.luyang.platform.uaa.common.enums.ErrorCode;
import io.github.luyang.platform.uaa.service.TokenService;
import io.github.luyang.platform.uaa.service.strategy.granter.TokenGranterHandler;
import io.github.luyang.starter.redisson.helper.RedissonHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 授权码模式处理器
 *
 * @author yang.lu
 */
@Component
@RequiredArgsConstructor
public class AuthorizationCodeGrantHandler implements TokenGranterHandler {

	private final TokenService tokenService;
	private final RedissonHelper redissonHelper;

	@Override
	public void grant(TokenApplyCommand command) {

		String stValue = CacheKey.AUTH_SSO_ST.of(command.code());
		// 未获取到授权码信息
		TempTicketDTO tempTicketDTO = redissonHelper.getString(stValue);
		ErrorCode.AUTH_AUTHORIZATION_CODE_NOT_FOUND.notNull(tempTicketDTO);

		// 重定向 URI 不匹配
		ErrorCode.AUTH_REDIRECT_URI_MISMATCH.isTrue(StrUtil.equals(command.redirectUri(), tempTicketDTO.redirectUri()));

		// 验证 codeVerifier
		boolean validateSuccess = tempTicketDTO.validatePkce(command.codeVerifier());
		ErrorCode.AUTH_PKCE_VERIFIER_FAILED.isTrue(validateSuccess);

		// 颁发 Token
//		tokenService.issueToken();

		// 删除ST 临时票据授权码
		redissonHelper.remove(stValue);
	}
}
