package io.github.luyang.platform.uac.mapper;

import io.github.luyang.platform.uac.beans.entity.UserEntity;
import io.github.luyang.starter.mybatis.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface UserMapper extends UltraMapper<UserEntity> {
}
