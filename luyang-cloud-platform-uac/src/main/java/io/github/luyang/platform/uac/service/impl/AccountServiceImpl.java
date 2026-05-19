package io.github.luyang.platform.uac.service.impl;

import io.github.luyang.platform.uac.common.enums.business.AccountType;
import io.github.luyang.platform.uac.repository.AccountRepository;
import io.github.luyang.platform.uac.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public boolean checkAccountUnique(String account, AccountType accountType) {
		return accountRepository.accountUnique(account, accountType);
	}
}
