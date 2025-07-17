package io.github.luyang.business.jalendar.calendar.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.jalendar.calendar.repository.mapper.CalendarMapper;
import io.github.luyang.business.jalendar.calendar.repository.model.CalendarDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 日历数据仓库
 *
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class CalendarRepository extends ServiceImpl<CalendarMapper, CalendarDO> {

	private final CalendarMapper calendarMapper;

	@Override
	public boolean save(CalendarDO calendarDO) {
		return super.save(calendarDO);
	}

	public CalendarDO findByCalendarId(String calendarId) {
		return calendarMapper.selectOne(CalendarDO::getCalendarId, calendarId);
	}
}
