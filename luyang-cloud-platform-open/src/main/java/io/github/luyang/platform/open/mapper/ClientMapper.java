package io.github.luyang.platform.open.mapper;

import io.github.luyang.platform.open.beans.entity.ClientEntity;
import io.github.luyang.starter.mybatis.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端相关 Mapper
 *
 * @author yang.lu
 */
@Mapper
public interface ClientMapper extends UltraMapper<ClientEntity> {
}
