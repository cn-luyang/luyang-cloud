package io.github.luyang.business.plan.subscribe;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.business.plan.calendar.CalendarService;
import io.github.luyang.business.plan.subscribe.beans.CalendarSubscribeConvert;
import io.github.luyang.business.plan.subscribe.beans.bo.InitSubscribeParam;
import io.github.luyang.business.plan.subscribe.beans.body.CalendarSubscribeRequest;
import io.github.luyang.business.plan.subscribe.beans.entity.CalendarSubscribeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarSubscribeService {

	private final CalendarSubscribeConvert calendarSubscribeConvert;
	private final CalendarSubscribeRepository calendarSubscribeRepository;
	private final AtomicReference<CalendarService> calendarServiceRef = new AtomicReference<>();

	private CalendarService getCalendarService() {
		return calendarServiceRef.updateAndGet(ref ->
			Objects.requireNonNullElseGet(ref, () -> SpringUtil.getBean(CalendarService.class))
		);
	}

	public void subscribe(CalendarSubscribeRequest calendarSubscribeRequest) {

	}

	public void initSubscribe(InitSubscribeParam initSubscribeParam) {
		CalendarSubscribeEntity entity = calendarSubscribeConvert.buildEntity(initSubscribeParam);
		calendarSubscribeRepository.save(entity);
	}
}
