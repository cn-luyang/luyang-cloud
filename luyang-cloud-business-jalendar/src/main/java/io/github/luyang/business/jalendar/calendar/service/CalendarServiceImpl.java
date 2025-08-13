package io.github.luyang.business.jalendar.calendar.service;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.business.jalendar.base.enums.error.CalendarError;
import io.github.luyang.business.jalendar.calendar.beans.convert.CalendarConvert;
import io.github.luyang.business.jalendar.calendar.beans.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarEntity;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 日历服务实现
 *
 * @author yang.lu
 */
@Service
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

	private static final Logger logger = LoggerFactory.getLogger(CalendarServiceImpl.class);

	private final CalendarRepository calendarRepository;

	private final CalendarSubscribeService calendarSubscribeService;

	@Override
	@Transactional
	public String createCalendar(CalendarCreateRequest calendarCreateRequest) {

		// 生成日历ID
		String calendarId = IdUtil.nanoId();

		// 构建数据库实体
		CalendarEntity calendarEntity = CalendarConvert.buildEntity(calendarId, calendarCreateRequest);
		CalendarError.INCOMPLETE_CALENDAR.notNull(calendarEntity);

		// 入库日历数据
		calendarRepository.save(calendarEntity);

		// 初始订阅日历
		calendarSubscribeService.initSubscribe(calendarId, calendarCreateRequest);

		return calendarId;
	}

	@Override
	public CalendarDomain getCalendarDomain(String calendarId) {
		CalendarEntity calendarEntity = calendarRepository.getById(calendarId);
		return CalendarConvert.buildDomain(calendarEntity);
	}
}
