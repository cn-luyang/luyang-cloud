package io.github.luyang.platform.uac.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.beans.UserDO;
import io.github.luyang.platform.uac.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class UserRepository extends ServiceImpl<UserMapper, UserDO> {

	public boolean emailUnique(String email) {
		return this.lambdaQuery()
			.eq(UserDO::getEmail, email)
			.exists();
	}
}
