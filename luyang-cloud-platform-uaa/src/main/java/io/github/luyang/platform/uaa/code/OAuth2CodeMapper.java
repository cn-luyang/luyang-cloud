package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa.code.beans.entity.OAuth2CodeEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface OAuth2CodeMapper extends UltraMapper<OAuth2CodeEntity> {
}
