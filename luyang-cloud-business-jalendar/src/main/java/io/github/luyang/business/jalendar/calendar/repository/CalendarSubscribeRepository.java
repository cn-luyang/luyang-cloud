package io.github.luyang.business.jalendar.calendar.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarSubscribeEntity;
import io.github.luyang.business.jalendar.calendar.mapper.CalendarSubscribeMapper;
import org.springframework.stereotype.Repository;

/**
 * 日历订阅数据访问层
 *
 * @author yang.lu
 */
@Repository
public class CalendarSubscribeRepository extends ServiceImpl<CalendarSubscribeMapper, CalendarSubscribeEntity> {

	public boolean isRepeatSubscribe(String userId, String calendarId) {
		return lambdaQuery()
			.eq(CalendarSubscribeEntity::getUserId, userId)
			.eq(CalendarSubscribeEntity::getCalendarId, calendarId)
			.eq(CalendarSubscribeEntity::getUnsubscribe, true)
			.exists();
	}
}
