package io.github.luyang.business.jalendar.calendar.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.business.jalendar.base.enums.error.CalendarError;
import io.github.luyang.business.jalendar.calendar.beans.convert.CalendarSubscribeConvert;
import io.github.luyang.business.jalendar.calendar.beans.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarSubscribeEntity;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarSubscribeRequest;
import io.github.luyang.business.jalendar.calendar.repository.CalendarSubscribeRepository;
import io.github.luyang.starter.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 日历订阅服务实现
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarSubscribeServiceImpl implements CalendarSubscribeService {

	private final static Logger logger = LoggerFactory.getLogger(CalendarSubscribeServiceImpl.class);

	private final CalendarSubscribeRepository calendarSubscribeRepository;

	private final AtomicReference<CalendarService> calendarServiceRef = new AtomicReference<>();

	private CalendarService getCalendarService() {
		return calendarServiceRef.updateAndGet(ref ->
			Objects.requireNonNullElseGet(ref, () -> SpringUtil.getBean(CalendarService.class))
		);
	}

	@Override
	public void initSubscribe(String calendarId, CalendarCreateRequest calendarCreateRequest) {

		List<CalendarSubscribeEntity> subscribeEntities = CalendarSubscribeConvert.buildEntities(calendarId, calendarCreateRequest);
		if (CollUtil.isEmpty(subscribeEntities)) return;
		calendarSubscribeRepository.saveBatch(subscribeEntities);
	}

	@Override
	public void subscribeCalendar(CalendarSubscribeRequest request) {

		// 获取日历领域对象
		CalendarDomain calendarDomain = getCalendarService().getCalendarDomain(request.calendarId());
		// 校验日历是否存在
		CalendarError.NOT_FOUND_CALENDAR.notNull(calendarDomain);
		// 检查日历是否允许订阅（非私密日历）
		CalendarError.NOT_ALLOWED_SUBSCRIBE.notNull(calendarDomain.isPrivate());

		//  获取当前用户ID
		String userId = SecurityUtil.getUserId();

		// 检查是否已订阅过该日历
		boolean isRepeatSubscribe = calendarSubscribeRepository.isRepeatSubscribe(userId, calendarDomain.calendarId());
		CalendarError.REPEAT_SUBSCRIBE.isFalse(isRepeatSubscribe);

		// 构建订阅实体并保存
		CalendarSubscribeEntity calendarSubscribeEntity = CalendarSubscribeConvert.buildEntity(userId, calendarDomain);
		calendarSubscribeRepository.save(calendarSubscribeEntity);
	}
}
