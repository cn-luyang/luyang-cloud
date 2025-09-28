package io.github.luyang.platform.uaa.token;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.uaa.token.beans.entity.TokenEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface TokenMapper extends UltraMapper<TokenEntity>, BaseMapper<TokenEntity> {
}
