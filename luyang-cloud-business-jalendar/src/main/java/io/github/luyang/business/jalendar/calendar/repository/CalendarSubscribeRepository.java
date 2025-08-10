package io.github.luyang.business.jalendar.calendar.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarSubscribeEntity;
import io.github.luyang.business.jalendar.calendar.repository.mapper.CalendarSubscribeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 日历数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class CalendarSubscribeRepository extends ServiceImpl<CalendarSubscribeMapper, CalendarSubscribeEntity> {

	private final CalendarSubscribeMapper calendarSubscribeMapper;

}
