package io.github.luyang.platform.uac.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.beans.entity.UserEntity;
import io.github.luyang.platform.uac.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class UserRepository extends ServiceImpl<UserMapper, UserEntity> {

	private final UserMapper userMapper;

	public boolean existsEmail(String email) {
		return lambdaQuery().eq(UserEntity::getEmail, email).exists();
	}
}
