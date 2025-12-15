package io.github.luyang.platform.uaa.client;

import io.github.luyang.platform.uaa.client.beans.entity.OAuth2ClientEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * OAuth2 客户端数据访问接口
 *
 * @author yang.lu
 */
@Mapper
public interface OAuth2ClientMapper extends UltraMapper<OAuth2ClientEntity> {
}
