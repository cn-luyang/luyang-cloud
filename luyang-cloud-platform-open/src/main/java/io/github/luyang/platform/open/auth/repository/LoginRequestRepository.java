package io.github.luyang.platform.open.auth.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.auth.repository.entity.LoginRequestEntity;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
public class LoginRequestRepository extends ServiceImpl<LoginRequestMapper, LoginRequestEntity> {

}
