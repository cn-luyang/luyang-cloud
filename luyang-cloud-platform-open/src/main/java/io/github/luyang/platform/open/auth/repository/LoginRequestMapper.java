package io.github.luyang.platform.open.auth.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.open.auth.repository.entity.LoginRequestEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRequestMapper extends BaseMapper<LoginRequestEntity> {
}
