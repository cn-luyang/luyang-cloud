package io.github.luyang.business.plan.calendar;

import io.github.luyang.business.plan.calendar.beans.body.CalendarCreateRequest;
import io.github.luyang.business.plan.calendar.beans.body.CalendarCreateResponse;
import io.github.luyang.starter.base.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;

	@PostMapping
	public Result<CalendarCreateResponse> create(@RequestBody CalendarCreateRequest calendarCreateRequest) {
		return Result.success(calendarService.create(calendarCreateRequest));
	}
}
