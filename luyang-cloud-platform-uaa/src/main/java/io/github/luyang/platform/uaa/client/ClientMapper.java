package io.github.luyang.platform.uaa.client;

import io.github.luyang.platform.uaa.client.beans.entity.ClientEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface ClientMapper extends UltraMapper<ClientEntity> {
}
