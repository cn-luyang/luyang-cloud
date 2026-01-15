package io.github.luyang.platform.uaa.code;

import io.github.luyang.platform.uaa.code.beans.entity.AuthorizationCodeEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface AuthorizationCodeMapper extends UltraMapper<AuthorizationCodeEntity> {
}
