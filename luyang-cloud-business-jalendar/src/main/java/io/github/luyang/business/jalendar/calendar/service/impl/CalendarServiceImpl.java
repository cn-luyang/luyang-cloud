package io.github.luyang.business.jalendar.calendar.service.impl;

import io.github.luyang.business.jalendar.base.converter.CalendarConverter;
import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.dto.CreateCalendarDTO;
import io.github.luyang.business.jalendar.calendar.domain.dto.GetCalendarSummaryDTO;
import io.github.luyang.business.jalendar.calendar.repository.CalendarRepository;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
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
	public CreateCalendarDTO create(CreateCalendarCommand command) {

		CalendarEntity calendarEntity = calendarConverter.buildEntity(command);
		calendarRepository.save(calendarEntity);

		String calendarId = calendarEntity.getCalendarId();

		subscribeService.initSubscribe(calendarId, command);

		return CreateCalendarDTO.build(calendarId);
	}

	@Override
	public GetCalendarSummaryDTO getSummary(String calendarId) {

		CalendarEntity calendarEntity = calendarRepository.findByCalendarId(calendarId);

		return calendarConverter.buildGetCalendarSummaryDTO(calendarEntity);
	}
}
