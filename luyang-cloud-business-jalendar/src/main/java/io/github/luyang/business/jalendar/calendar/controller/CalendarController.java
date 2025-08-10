package io.github.luyang.business.jalendar.calendar.controller;

import io.github.luyang.business.jalendar.calendar.controller.request.CreateCalendarRequest;
import io.github.luyang.business.jalendar.calendar.controller.response.CreateCalendarResponse;
import io.github.luyang.business.jalendar.calendar.converter.CalendarConverter;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.business.jalendar.calendar.service.command.CalendarCommand;
import io.github.luyang.starter.base.api.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日历相关控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;
	private final CalendarConverter calendarConverter;

	@PostMapping
	public Result<CreateCalendarResponse> createCalendar(CreateCalendarRequest request) {
		CalendarCommand command = calendarConverter.buildCommand(request);
		String calendarId = calendarService.createCalendar(command);
		return Result.success(CreateCalendarResponse.build(calendarId));
	}
}
