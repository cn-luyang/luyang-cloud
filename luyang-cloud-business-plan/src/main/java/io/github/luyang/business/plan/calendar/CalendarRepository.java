package io.github.luyang.business.plan.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author yang.lu
 */
@Repository
@RequiredArgsConstructor
public class CalendarRepository {

	private final CalendarService calendarService;
}
