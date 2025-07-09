package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.domain.CalendarCommand;

/**
 * 日历业务服务接口
 *
 * @author yang.lu
 */
public interface CalendarService {

	/**
	 * 创建日历
	 *
	 * @param command 日历创建命令对象
	 * @return 日历业务对象
	 * @author yang.lu
	 */
	void create(CalendarCommand command);
}
