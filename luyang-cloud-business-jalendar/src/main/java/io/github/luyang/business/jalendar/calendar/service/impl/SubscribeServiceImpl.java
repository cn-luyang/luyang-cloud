package io.github.luyang.business.jalendar.calendar.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import io.github.luyang.business.jalendar.base.bean.CalendarSharedUser;
import io.github.luyang.business.jalendar.base.converter.SubscribeConverter;
import io.github.luyang.business.jalendar.base.enums.error.CalendarError;
import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;
import io.github.luyang.business.jalendar.calendar.repository.SubscribeRepository;
import io.github.luyang.business.jalendar.calendar.repository.model.SubscribeDO;
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
	public void initSubscribe(CalendarCommand command) {

		List<SubscribeDO> subscribeDOs = new ArrayList<>();

		// 拥有者订阅
		SubscribeDO ownerSubscribeDO = subscribeConverter.toOwnerSubscribeDO(command);
		subscribeDOs.add(ownerSubscribeDO);

		// 共享用户订阅
		List<CalendarSharedUser> calendarSharedUsers = CalendarSharedUser.deduplicateByUserId(command.sharedUsers());
		calendarSharedUsers.forEach(sharedUser -> {
			SubscribeDO shareSubscribeDO = subscribeConverter.toSharedSubscribeDO(command, sharedUser);
			subscribeDOs.add(shareSubscribeDO);
		});

		this.subscribeRepository.saveBatch(subscribeDOs);
	}

	@Override
	public void subscribe(SubscribeCommand command) {

		CalendarDomain calendarDomain = getCalendarService().get(command.calendarId());
		CalendarError.NOT_FOUND_CALENDAR.isNull(calendarDomain);

		// 私密日历不允许订阅
		CalendarError.NOT_ALLOWED_SUBSCRIBE.isFalse(calendarDomain.isPrivateCalendar());

		SubscribeDO subscribeDO = subscribeConverter.toDO(command);
		subscribeDO.insertOrUpdate();
	}
}
