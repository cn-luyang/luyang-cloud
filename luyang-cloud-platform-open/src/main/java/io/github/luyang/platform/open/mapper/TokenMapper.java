package io.github.luyang.platform.open.mapper;

import io.github.luyang.platform.open.beans.entity.TokenEntity;
import io.github.luyang.starter.mybatis.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Token 相关 Mapper
 *
 * @author yang.lu
 */
@Mapper
public interface TokenMapper extends UltraMapper<TokenEntity> {
}
