package io.github.luyang.business.jalendar.calendar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日历数据映射接口
 *
 * @author yang.lu
 */
@Mapper
public interface CalendarMapper extends BaseMapper<CalendarEntity> {
}
