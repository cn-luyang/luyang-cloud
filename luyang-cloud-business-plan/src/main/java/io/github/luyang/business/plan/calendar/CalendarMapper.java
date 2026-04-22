package io.github.luyang.business.plan.calendar;

import io.github.luyang.business.plan.calendar.beans.entity.CalendarEntity;
import io.github.luyang.starter.mybatis.support.mapper.UltraMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yang.lu
 */
@Mapper
public interface CalendarMapper extends UltraMapper<CalendarEntity> {
}
