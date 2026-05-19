package io.github.luyang.platform.uac.service;

import io.github.luyang.platform.uac.common.enums.business.AccountType;

public interface AccountService {

	boolean checkAccountUnique(String account, AccountType accountType);
}
