package io.github.luyang.platform.open.auth.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.open.auth.repository.entity.AuthRequestEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AutRequestMapper extends BaseMapper<AuthRequestEntity> {
}
