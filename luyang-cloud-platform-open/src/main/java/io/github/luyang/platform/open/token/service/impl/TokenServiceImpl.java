package io.github.luyang.platform.open.token.service.impl;

import io.github.luyang.platform.open.base.converter.TokenConvert;
import io.github.luyang.platform.open.token.domain.TokenCommand;
import io.github.luyang.platform.open.token.domain.TokenDomain;
import io.github.luyang.platform.open.token.repository.TokenRepository;
import io.github.luyang.platform.open.token.repository.model.TokenDO;
import io.github.luyang.platform.open.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Token 业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final TokenConvert convert;
	private final TokenRepository repository;

	/**
	 * 创建用户Token
	 *
	 * @param command 创建用户Token命令对象
	 * @return Token业务对象
	 * @author yang.lu
	 */
	@Override
	public TokenDomain createUserToken(TokenCommand command) {

		// 删除有效的 Token 记录，确保不会为同一个客户端和用户创建多个 Token
		this.repository.removeByClientIdAndUserId(command.clientId(), command.userId());

		// TokenCommand 转换为 TokenDO
		TokenDO tokenDO = this.convert.toDO(command);
		// 持久化到数据库
		this.repository.save(tokenDO);

		// TokenDO 换为 TokenDomain
		return this.convert.toDomain(tokenDO);
	}
}
