package io.github.luyang.platform.uaa.token;

import io.github.luyang.platform.uaa.token.beans.OAuth2TokenConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class OAuth2TokenService {

	private final OAuth2TokenRepository tokenRepository;
	private final OAuth2TokenConvert tokenConvert;

	public void issueToken() {

	}

}
