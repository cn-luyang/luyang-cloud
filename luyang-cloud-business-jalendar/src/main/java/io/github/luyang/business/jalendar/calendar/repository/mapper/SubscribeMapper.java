package io.github.luyang.business.jalendar.calendar.repository.mapper;

import io.github.luyang.business.jalendar.calendar.repository.model.SubscribeDO;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubscribeMapper extends DataBaseMapper<SubscribeDO> {
}
