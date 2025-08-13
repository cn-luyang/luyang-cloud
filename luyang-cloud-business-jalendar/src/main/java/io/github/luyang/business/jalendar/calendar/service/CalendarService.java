package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.beans.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;

/**
 * 日历服务接口
 *
 * @author yang.lu
 */
public interface CalendarService {

	/**
	 * 创建日历
	 *
	 * @param calendarCreateRequest 日历创建请求
	 * @return 日历ID
	 * @author yang.lu
	 */
	String createCalendar(CalendarCreateRequest calendarCreateRequest);

	CalendarDomain getCalendarDomain(String calendarId);
}
