package io.github.luyang.platform.open.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.auth.repository.entity.AuthRequestEntity;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
public class AuthRequestRepository extends ServiceImpl<AutRequestMapper, AuthRequestEntity> {
}
