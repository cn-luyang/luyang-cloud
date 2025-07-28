package io.github.luyang.business.jalendar.calendar;

import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.dto.CreateCalendarDTO;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.starter.base.api.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日历 RESTful API 控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;

	@PostMapping
	public Result<CreateCalendarDTO> createSharedCalendar(@Valid @RequestBody CreateCalendarCommand command) {
		return Result.success(calendarService.create(command));
	}

	
}
