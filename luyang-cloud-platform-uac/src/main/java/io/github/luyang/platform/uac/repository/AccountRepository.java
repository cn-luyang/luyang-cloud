package io.github.luyang.platform.uac.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.beans.AccountDO;
import io.github.luyang.platform.uac.common.enums.business.AccountType;
import io.github.luyang.platform.uac.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class AccountRepository extends ServiceImpl<AccountMapper, AccountDO> {

	private final AccountMapper accountMapper;

	public boolean accountUnique(String account, AccountType accountType) {
		return this.lambdaQuery()
			.eq(AccountDO::getAccount, account)
			.eq(AccountDO::getAccountType, accountType)
			.exists();
	}
}
