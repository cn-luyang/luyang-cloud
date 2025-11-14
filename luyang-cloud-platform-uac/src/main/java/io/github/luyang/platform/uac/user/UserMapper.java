package io.github.luyang.platform.uac.user;

import io.github.luyang.platform.uac.user.beans.entity.UserEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface UserMapper extends UltraMapper<UserEntity> {
}
