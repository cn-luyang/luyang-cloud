package io.github.luyang.platform.uaa.remote;

import io.github.luyang.platform.uaa.beans.TokenDO;
import io.github.luyang.platform.uaa.repository.TokenRepository;
import io.github.luyang.starter.base.model.Result;
import io.github.luyang.starter.security.support.remote.RemoteAuthClient;
import io.github.luyang.starter.security.support.remote.TokenValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
public class RemoteAuthClientImpl implements RemoteAuthClient {

	private final TokenRepository tokenRepository;

	@Override
	public Result<TokenValidationResponse> validateToken(String token) {

		TokenDO tokenDO = tokenRepository.findByAccessToken(token);
		if (null == tokenDO) {
			return Result.success();
		}

		TokenValidationResponse response = new TokenValidationResponse();
		LocalDateTime accessTokenExpiresTime = tokenDO.getAccessTokenExpiresTime();

		response.setExpired(LocalDateTime.now().isAfter(accessTokenExpiresTime));
		response.setUserId(tokenDO.getUserId());
		response.setCnName("32123");
		response.setAccessTokenExpiresTime(tokenDO.getAccessTokenExpiresTime());

		return Result.success(response);
	}
}
