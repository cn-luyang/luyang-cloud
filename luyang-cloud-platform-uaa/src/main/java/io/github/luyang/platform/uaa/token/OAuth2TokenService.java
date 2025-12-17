package io.github.luyang.platform.uaa.token;

import io.github.luyang.platform.uaa.token.beans.OAuth2TokenConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * OAuth2 令牌业务服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2TokenService {

	private final OAuth2TokenRepository tokenRepository;
	private final OAuth2TokenConvert tokenConvert;

	/**
	 * 签发令牌
	 */
	public void issueToken() {
	}
}
