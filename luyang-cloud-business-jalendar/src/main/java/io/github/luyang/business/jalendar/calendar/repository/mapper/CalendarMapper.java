package io.github.luyang.business.jalendar.calendar.repository.mapper;

import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
import io.github.luyang.starter.mybatis.support.DataBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper extends DataBaseMapper<CalendarEntity> {
}
