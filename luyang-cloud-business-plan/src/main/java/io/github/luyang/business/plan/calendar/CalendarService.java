package io.github.luyang.business.plan.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarService {

	private final CalendarRepository calendarRepository;
}
