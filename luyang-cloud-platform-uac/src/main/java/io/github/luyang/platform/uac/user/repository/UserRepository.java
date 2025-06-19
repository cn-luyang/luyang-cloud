package io.github.luyang.platform.uac.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.base.valueobject.Email;
import io.github.luyang.platform.uac.base.valueobject.UserId;
import io.github.luyang.platform.uac.user.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * 用户相关 Repository
 *
 * @author yang.lu
 */
@Repository
public class UserRepository extends ServiceImpl<UserMapper, UserEntity> {

	public boolean unique(UserId userId) {
		return this.baseMapper.exists(UserEntity::getUserId, userId.value());
	}

	public boolean unique(Email email) {
		return this.baseMapper.exists(UserEntity::getEmail, email.value());
	}

	public UserEntity find(Email email) {
		return this.baseMapper.selectOne(UserEntity::getEmail, email.value());
	}
}
