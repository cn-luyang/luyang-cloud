package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;

public interface SubscribeService {

	void initSubscribe(String calendarId, CreateCalendarCommand command);
}
