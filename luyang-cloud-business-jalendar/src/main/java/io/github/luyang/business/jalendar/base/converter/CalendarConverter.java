package io.github.luyang.business.jalendar.base.converter;

import io.github.luyang.business.jalendar.calendar.controller.request.CalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.domain.CalendarCommand;
import org.mapstruct.Mapper;

/**
 * 日历对象转换器
 *
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface CalendarConverter {

	CalendarCommand toCommand(CalendarCreateRequest request);
}
