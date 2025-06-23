package io.github.luyang.platform.uac.user.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uac.user.repository.model.UserDO;
import org.springframework.stereotype.Repository;

/**
 * 用户数据仓库
 *
 * @author yang.lu
 */
@Repository
public class UserRepository extends ServiceImpl<UserMapper, UserDO> {

	public boolean existsEmail(String email) {
		return this.baseMapper.exists(UserDO::getEmail, email);
	}

	public UserDO findByEmail(String email) {
		return this.baseMapper.selectOne(UserDO::getEmail, email);
	}
}
