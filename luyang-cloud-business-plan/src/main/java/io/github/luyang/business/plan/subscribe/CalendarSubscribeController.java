package io.github.luyang.business.plan.subscribe;

import io.github.luyang.business.plan.subscribe.beans.body.CalendarSubscribeRequest;
import io.github.luyang.starter.base.common.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang.lu
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar-subscribe")
public class CalendarSubscribeController {

	private final CalendarSubscribeService calendarSubscribeService;

	@PostMapping()
	public Result<Void> subscribe(CalendarSubscribeRequest calendarSubscribeRequest) {
		calendarSubscribeService.subscribe(calendarSubscribeRequest);
		return Result.success();
	}
}
