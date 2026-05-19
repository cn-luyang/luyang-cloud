package io.github.luyang.platform.uac.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.beans.AccountEntity;
import io.github.luyang.platform.uac.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class AccountRepository extends ServiceImpl<AccountMapper, AccountEntity> {

	private final AccountMapper accountMapper;

	public List<AccountEntity> findByUsernames(List<String> usernames) {
		return this.lambdaQuery().in(AccountEntity::getUsername, usernames).list();
	}
}
