package io.github.luyang.business.plan.calendar;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.business.plan.calendar.beans.CalendarConvert;
import io.github.luyang.business.plan.calendar.beans.CalendarDomain;
import io.github.luyang.business.plan.calendar.beans.body.CalendarCreateRequest;
import io.github.luyang.business.plan.calendar.beans.body.CalendarCreateResponse;
import io.github.luyang.business.plan.calendar.beans.entity.CalendarEntity;
import io.github.luyang.business.plan.subscribe.CalendarSubscribeService;
import io.github.luyang.business.plan.subscribe.beans.bo.InitSubscribeParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarService {

	private final CalendarRepository calendarRepository;
	private final CalendarConvert calendarConvert;
	private final CalendarSubscribeService calendarSubscribeService;

	@Transactional
	public CalendarCreateResponse create(CalendarCreateRequest calendarCreateRequest) {

		// 生成日历ID
		String calendarId = IdUtil.nanoId();

		// 入库日历数据
		CalendarEntity calendarEntity = calendarConvert.buildEntity(calendarId, calendarCreateRequest);
		calendarRepository.save(calendarEntity);

		// 初始订阅日历
		InitSubscribeParam initSubscribeParam = calendarConvert.buildInitSubscribeParam(calendarId, calendarEntity);
		calendarSubscribeService.initSubscribe(initSubscribeParam);

		return CalendarCreateResponse.build(calendarId);
	}

	public CalendarDomain getDomain(String calendarId) {
		CalendarEntity calendarEntity = calendarRepository.getById(calendarId);
		return calendarConvert.buildDomain(calendarEntity);
	}
}
