package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.service.command.CalendarCommand;

/**
 * @author yang.lu
 */
public interface CalendarSubscribeService {

	void initSubscribe(CalendarCommand command);
}
