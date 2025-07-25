package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;

public interface SubscribeService {

	/**
	 * 初始订阅(创建日历时)
	 *
	 * @param command 日历创建命令对象
	 * @author yang.lu
	 */
	void initSubscribe(CalendarCommand command);

	void subscribe(SubscribeCommand command);
}
