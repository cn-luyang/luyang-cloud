package io.github.luyang.business.jalendar.calendar.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
import io.github.luyang.business.jalendar.calendar.repository.mapper.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 日历数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class CalendarRepository extends ServiceImpl<CalendarMapper, CalendarEntity> {

	private final CalendarMapper calendarMapper;

	@Override
	public boolean save(CalendarEntity calendarEntity) {
		return super.save(calendarEntity);
	}

	public CalendarEntity findByCalendarId(String calendarId) {
		return calendarMapper.selectOne(CalendarEntity::getCalendarId, calendarId);
	}

//	public CalendarJO findByCalendarId(String calendarId) {
//		return calendarMapper.findByCalendarId(calendarId);
//	}
}
