package io.github.luyang.business.jalendar.base.converter;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarType;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;
import io.github.luyang.business.jalendar.calendar.controller.request.SharedCalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.controller.response.CalendarResponse;
import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.repository.model.CalendarDO;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.security.util.SecurityUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 日历对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		SecurityUtil.class,
		IBaseEnum.class,
		CalendarColor.class,
		CalendarType.class,
		CalendarVisibility.class,
		IdUtil.class
	})
public interface CalendarConverter {

	CalendarCommand toCommand(SharedCalendarCreateRequest request);

	@Mapping(target = "calendarId", expression = "java(IdUtil.simpleUUID())")
	@Mapping(target = "userId", expression = "java(SecurityUtil.getUserId())")
	@Mapping(target = "calendarType", constant = "SHARED")
	@Mapping(target = "defaultName", source = "calendarName")
	@Mapping(target = "defaultColor", source = "calendarColor")
	CalendarDO toSharedCalendarDO(CalendarCommand command);

	CalendarDomain toDomain(CalendarDO calendarDO);

	CalendarResponse toResponse(CalendarDomain calendarDomain);
}
