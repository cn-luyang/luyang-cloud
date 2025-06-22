package io.github.luyang.platform.open.v1.client.repository;

import io.github.luyang.platform.open.v1.client.repository.entity.ClientDO;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端相关 Mapper
 *
 * @author yang.lu
 */
@Mapper
public interface ClientMapper extends DataBaseMapper<ClientDO> {
}
