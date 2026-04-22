package io.github.luyang.platform.uac.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.user.beans.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class UserRepository extends ServiceImpl<UserMapper, UserEntity> {

	private final UserMapper userMapper;

	public boolean emailUnique(String email) {
		return lambdaQuery().eq(UserEntity::getEmail, email).exists();
	}

	public UserEntity findByEmail(String email) {
		return lambdaQuery().eq(UserEntity::getEmail, email).one();
	}
}
