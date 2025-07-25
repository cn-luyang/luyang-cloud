package io.github.luyang.business.jalendar.calendar.controller;

import io.github.luyang.business.jalendar.base.converter.CalendarConverter;
import io.github.luyang.business.jalendar.base.converter.SubscribeConverter;
import io.github.luyang.business.jalendar.calendar.controller.request.CalendarSubscribeRequest;
import io.github.luyang.business.jalendar.calendar.controller.request.SharedCalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.controller.response.CalendarResponse;
import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.business.jalendar.calendar.service.SubscribeService;
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
	private final SubscribeService subscribeService;

	private final CalendarConverter calendarConverter;
	private final SubscribeConverter subscribeConverter;

	@PostMapping("/shared")
	public Result<CalendarResponse> createSharedCalendar(@Valid @RequestBody SharedCalendarCreateRequest request) {
		CalendarCommand command = calendarConverter.toCommand(request);
		CalendarDomain calendarDomain = calendarService.createSharedCalendar(command);
		return Result.success(calendarConverter.toResponse(calendarDomain));
	}

	@PostMapping("/subscribe")
	public Result<Void> subscribe(@Valid @RequestBody CalendarSubscribeRequest request) {
		SubscribeCommand command = subscribeConverter.toCommand(request);
		subscribeService.subscribe(command);
		return Result.success();
	}
}
