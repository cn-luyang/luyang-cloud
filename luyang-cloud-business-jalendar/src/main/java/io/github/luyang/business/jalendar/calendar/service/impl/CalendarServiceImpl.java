package io.github.luyang.business.jalendar.calendar.service.impl;

import io.github.luyang.business.jalendar.base.converter.CalendarConverter;
import io.github.luyang.business.jalendar.calendar.domain.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.repository.CalendarRepository;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
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

	private final CalendarRepository calendarRepository;
	private final CalendarConverter calendarConverter;

	@Override
	@Transactional
	public void create(CalendarCommand command) {

	}
}
