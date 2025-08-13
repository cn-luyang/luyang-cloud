package io.github.luyang.business.jalendar.calendar.controller;

import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarSubscribeRequest;
import io.github.luyang.business.jalendar.calendar.beans.response.CalendarCreateResponse;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.business.jalendar.calendar.service.CalendarSubscribeService;
import io.github.luyang.starter.base.api.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日历控制器
 *
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;
	private final CalendarSubscribeService calendarSubscribeService;

	@PostMapping
	public Result<CalendarCreateResponse> createCalendar(@RequestBody CalendarCreateRequest request) {
		String calendarId = calendarService.createCalendar(request);
		return Result.success(CalendarCreateResponse.build(calendarId));
	}

	@PostMapping("/subscribe")
	public Result<Void> subscribeCalendar(CalendarSubscribeRequest request) {
		calendarSubscribeService.subscribeCalendar(request);
		return Result.success();
	}
}
