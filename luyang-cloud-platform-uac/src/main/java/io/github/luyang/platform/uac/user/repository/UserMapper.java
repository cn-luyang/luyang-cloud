package io.github.luyang.platform.uac.user.repository;

import io.github.luyang.platform.uac.user.repository.entity.UserEntity;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends DataBaseMapper<UserEntity> {
}
