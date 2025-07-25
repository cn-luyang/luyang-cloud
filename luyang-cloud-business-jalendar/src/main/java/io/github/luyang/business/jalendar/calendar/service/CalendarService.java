package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;

/**
 * 日历业务服务接口
 *
 * @author yang.lu
 */
public interface CalendarService {

	/**
	 * 创建共享日历
	 *
	 * @param command 日历创建命令对象
	 * @return 日历业务对象
	 * @author yang.lu
	 */
	CalendarDomain createSharedCalendar(CalendarCommand command);

	/**
	 * 获取日历详情
	 *
	 * @param calendarId 日历
	 * @return 日历业务对象
	 * @author yang.lu
	 */
	CalendarDomain get(String calendarId);
}
