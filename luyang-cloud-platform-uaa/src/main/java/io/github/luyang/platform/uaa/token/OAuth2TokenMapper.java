package io.github.luyang.platform.uaa.token;

import io.github.luyang.platform.uaa.token.beans.entity.OAuth2TokenEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface OAuth2TokenMapper extends UltraMapper<OAuth2TokenEntity> {
}
