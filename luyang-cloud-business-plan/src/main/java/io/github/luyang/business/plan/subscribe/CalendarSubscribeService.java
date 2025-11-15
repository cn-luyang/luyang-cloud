package io.github.luyang.business.plan.subscribe;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.business.plan._common.enums.error.CalendarError;
import io.github.luyang.business.plan.calendar.CalendarService;
import io.github.luyang.business.plan.calendar.beans.CalendarDomain;
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

		String calendarId = calendarSubscribeRequest.calendarId();

		// 获取日历领域对象
		CalendarDomain calendarDomain = getCalendarService().getDomain(calendarId);
		// 校验日历是否存在
		CalendarError.NOT_FOUND_CALENDAR.notNull(calendarDomain);
		// 检查日历是否允许订阅（非私密日历）
		CalendarError.NOT_ALLOWED_SUBSCRIBE.notNull(calendarDomain.isPrivate());

		//  获取当前用户ID
		String userId = null;
		// 检查是否已订阅过该日历
		boolean isRepeatSubscribe = calendarSubscribeRepository.isRepeatSubscribe(userId, calendarId);
		CalendarError.REPEAT_SUBSCRIBE.isFalse(isRepeatSubscribe);

		// 构建订阅实体并保存
		CalendarSubscribeEntity calendarSubscribeEntity = calendarSubscribeConvert.buildEntity(userId, calendarDomain);
		calendarSubscribeRepository.save(calendarSubscribeEntity);
	}

	public void initSubscribe(InitSubscribeParam initSubscribeParam) {
		CalendarSubscribeEntity entity = calendarSubscribeConvert.buildEntity(initSubscribeParam);
		calendarSubscribeRepository.save(entity);
	}
}
