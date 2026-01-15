package io.github.luyang.platform.uaa.token;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.platform.uaa.token.beans.entity.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 令牌数据访问层
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class TokenRepository extends ServiceImpl<TokenMapper, TokenEntity> {

}
