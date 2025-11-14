package io.github.luyang.business.plan.calendar.beans;

import io.github.luyang.business.plan.calendar.beans.body.CalendarCreateRequest;
import io.github.luyang.business.plan.calendar.beans.entity.CalendarEntity;
import io.github.luyang.business.plan.subscribe.beans.bo.InitSubscribeParam;
import org.mapstruct.Mapper;

/**
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface CalendarConvert {

	CalendarEntity buildEntity(String calendarId, CalendarCreateRequest calendarCreateRequest);

	InitSubscribeParam buildInitSubscribeParam(String calendarId, CalendarCreateRequest calendarCreateRequest);
}
