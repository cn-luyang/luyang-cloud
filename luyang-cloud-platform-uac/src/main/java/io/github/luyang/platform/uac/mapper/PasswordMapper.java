package io.github.luyang.platform.uac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.uac.beans.PasswordEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasswordMapper extends BaseMapper<PasswordEntity> {
}
