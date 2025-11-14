package io.github.luyang.business.plan.calendar;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.plan.calendar.beans.entity.CalendarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class CalendarRepository extends ServiceImpl<CalendarMapper, CalendarEntity> {

	private final CalendarService calendarService;
}
