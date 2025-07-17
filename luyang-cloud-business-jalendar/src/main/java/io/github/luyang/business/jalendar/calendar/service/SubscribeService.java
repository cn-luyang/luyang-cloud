package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;

public interface SubscribeService {

	void subscribe(SubscribeCommand command);
}
