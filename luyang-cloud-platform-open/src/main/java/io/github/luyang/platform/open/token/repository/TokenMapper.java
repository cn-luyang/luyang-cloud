package io.github.luyang.platform.open.token.repository;

import io.github.luyang.platform.open.token.repository.entity.TokenDO;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface TokenMapper extends DataBaseMapper<TokenDO> {
}
