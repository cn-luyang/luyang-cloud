package io.github.luyang.business.jalendar.calendar.service.impl;

import io.github.luyang.business.jalendar.base.converter.CalendarConverter;
import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.repository.CalendarRepository;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
import io.github.luyang.business.jalendar.calendar.repository.entity.join.CalendarJO;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.business.jalendar.calendar.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日历业务服务实现类
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

	private static final Logger logger = LoggerFactory.getLogger(CalendarServiceImpl.class);

	private final SubscribeService subscribeService;
	private final CalendarRepository calendarRepository;
	private final CalendarConverter calendarConverter;

	@Override
	@Transactional
	public CalendarDomain createSharedCalendar(CalendarCommand command) {

		CalendarEntity calendarEntity = calendarConverter.toEntity(command);
		calendarEntity.insert();

		// 订阅日历
		subscribeService.initSubscribe(command);

		return calendarConverter.toDomain(calendarEntity);
	}

	@Override
	public CalendarDomain getDetail(String calendarId) {
		CalendarJO calendarJO = calendarRepository.findByCalendarId(calendarId);
		return calendarConverter.toDomain(calendarJO);
	}
}
