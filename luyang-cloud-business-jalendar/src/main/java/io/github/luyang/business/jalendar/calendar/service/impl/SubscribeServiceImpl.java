package io.github.luyang.business.jalendar.calendar.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.business.jalendar.base.converter.SubscribeConverter;
import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;
import io.github.luyang.business.jalendar.calendar.repository.SubscribeRepository;
import io.github.luyang.business.jalendar.calendar.repository.entity.SubscribeEntity;
import io.github.luyang.business.jalendar.calendar.service.CalendarService;
import io.github.luyang.business.jalendar.calendar.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

	private static final Logger logger = LoggerFactory.getLogger(SubscribeServiceImpl.class);

	private final AtomicReference<CalendarService> calendarServiceRef = new AtomicReference<>();
	private final SubscribeConverter subscribeConverter;
	private final SubscribeRepository subscribeRepository;

	private CalendarService getCalendarService() {
		return calendarServiceRef.updateAndGet(ref ->
			Objects.requireNonNullElseGet(ref, () -> SpringUtil.getBean(CalendarService.class))
		);
	}

	@Override
	public void initSubscribe(String calendarId, CreateCalendarCommand command) {

		List<SubscribeEntity> subscribeEntities = new ArrayList<>();

		// 构建日历拥有者订阅
		SubscribeEntity ownerSubscribeEntity = subscribeConverter.buildOwnerSubscribeEntity(calendarId, command);
		subscribeEntities.add(ownerSubscribeEntity);

		// 构建共享用户订阅
		List<SubscribeEntity> sharedUserSubscribeEntities = subscribeConverter.buildSharedSubscribeEntity(calendarId, command);
		subscribeEntities.addAll(sharedUserSubscribeEntities);

		subscribeRepository.saveBatch(subscribeEntities);
	}
}
