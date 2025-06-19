package io.github.luyang.platform.open.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.auth.repository.entity.AuthorizeRequestEntity;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
public class AuthorizeRequestRepository extends ServiceImpl<AuthorizeRequestMapper, AuthorizeRequestEntity> {

	@Override
	public boolean save(AuthorizeRequestEntity entity) {
		// TODO Hash结构存储
		return super.save(entity);
	}
}
