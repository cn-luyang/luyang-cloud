package io.github.luyang.platform.uac.service.impl;

import cn.hutool.core.collection.CollUtil;
import io.github.luyang.platform.uac.beans.AccountEntity;
import io.github.luyang.platform.uac.beans.command.CreateAccountCommand;
import io.github.luyang.platform.uac.beans.convert.AccountConvert;
import io.github.luyang.platform.uac.common.enums.ErrorCode;
import io.github.luyang.platform.uac.repository.AccountRepository;
import io.github.luyang.platform.uac.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final AccountConvert accountConvert;

	@Override
	public void createAccount(List<CreateAccountCommand> createAccountCommands) {

		// 过滤出账号列表
		List<String> usernames = createAccountCommands
			.stream()
			.map(CreateAccountCommand::username)
			.distinct()
			.toList();

		// 校验账号唯一性
		validateUsernameUnique(usernames);

		// 创建账号
		List<AccountEntity> accountEntities = accountConvert.buildAccountEntities(createAccountCommands);
		accountRepository.saveBatch(accountEntities);
	}

	private void validateUsernameUnique(List<String> usernames) {
		List<AccountEntity> accountEntities = accountRepository.findByUsernames(usernames);
		if (CollUtil.isEmpty(accountEntities)) {
			return;
		}

		for (AccountEntity entity : accountEntities) {
			switch (entity.getAccountType()) {
				case EMAIL -> ErrorCode.ACCOUNT_EXISTS_EMAIL.exception();
				case MOBILE -> ErrorCode.ACCOUNT_EXISTS_MOBILE.exception();
			}
		}
	}
}
