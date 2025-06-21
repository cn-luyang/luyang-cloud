package io.github.luyang.platform.open.token.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
public class TokenRepository extends ServiceImpl<TokenMapper, TokenDO> {

}
