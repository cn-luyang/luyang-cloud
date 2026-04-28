package io.github.luyang.platform.uaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.uaa.beans.ClientDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper extends BaseMapper<ClientDO> {
}
