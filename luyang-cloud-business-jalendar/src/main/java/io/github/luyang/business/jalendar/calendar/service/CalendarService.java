package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.dto.CreateCalendarDTO;
import io.github.luyang.business.jalendar.calendar.domain.dto.GetCalendarSummaryDTO;

/**
 * 日历业务服务接口
 *
 * @author yang.lu
 */
public interface CalendarService {

	CreateCalendarDTO create(CreateCalendarCommand command);

	GetCalendarSummaryDTO getSummary(String calendarId);
}
