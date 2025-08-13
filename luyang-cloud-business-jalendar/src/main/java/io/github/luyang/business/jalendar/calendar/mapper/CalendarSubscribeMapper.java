package io.github.luyang.business.jalendar.calendar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarSubscribeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日历订阅映射接口
 *
 * @author yang.lu
 */
@Mapper
public interface CalendarSubscribeMapper extends BaseMapper<CalendarSubscribeEntity> {
}
