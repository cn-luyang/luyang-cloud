package io.github.luyang.platform.open.auth.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.open.auth.repository.entity.AuthorizeRequestEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorizeRequestMapper extends BaseMapper<AuthorizeRequestEntity> {
}
