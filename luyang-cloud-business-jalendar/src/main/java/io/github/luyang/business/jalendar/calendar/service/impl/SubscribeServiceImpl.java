package io.github.luyang.business.jalendar.calendar.service.impl;

import io.github.luyang.business.jalendar.base.converter.SubscribeConverter;
import io.github.luyang.business.jalendar.base.enums.error.CalendarError;
import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;
import io.github.luyang.business.jalendar.calendar.repository.SubscribeRepository;
import io.github.luyang.business.jalendar.calendar.repository.model.SubscribeDO;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.business.jalendar.calendar.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeServiceImpl.class);

	private final CalendarService calendarService;
	private final SubscribeConverter subscribeConverter;
	private final SubscribeRepository subscribeRepository;

	public void initSubscribe(SubscribeCommand command) {
		SubscribeDO subscribeDO = subscribeConverter.toDO(command);
		subscribeDO.insert();
	}

	@Override
	public void subscribe(SubscribeCommand command) {

		CalendarDomain calendarDomain = calendarService.get(command.calendarId());
		CalendarError.NOT_FOUND_CALENDAR.isNull(calendarDomain);

		// 私密日历不允许订阅
		CalendarError.NOT_ALLOWED_SUBSCRIBE.isFalse(calendarDomain.isPrivateCalendar());

		SubscribeDO subscribeDO = subscribeConverter.toDO(command);
		subscribeDO.insertOrUpdate();
	}
}
