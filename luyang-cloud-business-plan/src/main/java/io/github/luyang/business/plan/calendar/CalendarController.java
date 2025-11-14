package io.github.luyang.business.plan.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarRepository calendarRepository;
}
