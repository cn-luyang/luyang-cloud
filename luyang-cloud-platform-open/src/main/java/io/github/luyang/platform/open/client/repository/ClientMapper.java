package io.github.luyang.platform.open.client.repository;

import io.github.luyang.platform.open.client.repository.entity.ClientEntity;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户端相关 Mapper
 *
 * @author yang.lu
 */
@Mapper
public interface ClientMapper extends DataBaseMapper<ClientEntity> {
}
