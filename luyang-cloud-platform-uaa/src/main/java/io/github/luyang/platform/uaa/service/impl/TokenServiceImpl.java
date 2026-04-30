package io.github.luyang.platform.uaa.service.impl;

import io.github.luyang.platform.uaa.beans.TokenDO;
import io.github.luyang.platform.uaa.beans.contract.ClientDomain;
import io.github.luyang.platform.uaa.beans.convert.TokenConvert;
import io.github.luyang.platform.uaa.repository.TokenRepository;
import io.github.luyang.platform.uaa.service.ClientService;
import io.github.luyang.platform.uaa.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final ClientService clientService;

	private final TokenRepository tokenRepository;
	private final TokenConvert tokenConvert;

	public void issueToken(String clientId, String userId) {
		// 获取客户端信息
		ClientDomain clientDomain = clientService.getDomain(clientId);
		// 获取用户信息

		TokenDO tokenDO = tokenConvert.buildEntity(clientDomain);
		tokenRepository.save(tokenDO);
	}
}
