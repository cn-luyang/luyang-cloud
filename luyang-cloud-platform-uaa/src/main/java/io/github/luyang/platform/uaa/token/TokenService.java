package io.github.luyang.platform.uaa.token;

import io.github.luyang.platform.uaa.token.beans.TokenConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 令牌业务服务
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenService {

	private final TokenRepository tokenRepository;
	private final TokenConvert tokenConvert;

	/**
	 * 签发令牌
	 */
	public void issueToken() {
	}
}
