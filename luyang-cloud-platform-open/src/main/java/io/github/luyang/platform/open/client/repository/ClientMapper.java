package io.github.luyang.platform.open.client.repository;

import io.github.luyang.platform.open.client.repository.model.ClientDO;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientMapper extends DataBaseMapper<ClientDO> {
}
