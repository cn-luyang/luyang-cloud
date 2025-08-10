package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.converter.SubscribeConverter;
import io.github.luyang.business.jalendar.calendar.repository.CalendarSubscribeRepository;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarSubscribeEntity;
import io.github.luyang.business.jalendar.calendar.service.command.CalendarCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarSubscribeServiceImpl implements CalendarSubscribeService {

	private final SubscribeConverter subscribeConverter;
	private final CalendarSubscribeRepository calendarSubscribeRepository;

	@Override
	public void initSubscribe(CalendarCommand command) {
		List<CalendarSubscribeEntity> subscribeEntities = subscribeConverter.buildEntity(command);
		calendarSubscribeRepository.saveBatch(subscribeEntities);
	}
}


