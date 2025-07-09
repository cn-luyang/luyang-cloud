package io.github.luyang.business.jalendar.calendar.controller;

import io.github.luyang.business.jalendar.base.converter.CalendarConverter;
import io.github.luyang.business.jalendar.calendar.controller.request.CalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.domain.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.starter.base.api.Result;
import io.undertow.client.ClientResponse;
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
	private final CalendarConverter calendarConverter;

	@PostMapping
	public Result<ClientResponse> create(@Valid @RequestBody CalendarCreateRequest request) {
		CalendarCommand command = this.calendarConverter.toCommand(request);
		this.calendarService.create(command);
		return Result.success();
	}
}
