package io.github.luyang.platform.open.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.platform.open.beans.entity.ClientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端相关 Mapper
 *
 * @author yang.lu
 */
@Mapper
public interface ClientMapper extends BaseMapper<ClientEntity> {
}
