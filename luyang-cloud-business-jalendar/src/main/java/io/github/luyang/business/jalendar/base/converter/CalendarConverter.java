package io.github.luyang.business.jalendar.base.converter;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarType;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;
import io.github.luyang.business.jalendar.calendar.controller.request.SharedCalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.controller.response.CalendarResponse;
import io.github.luyang.business.jalendar.calendar.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
import io.github.luyang.business.jalendar.calendar.repository.entity.join.CalendarJO;
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

	@Mapping(target = "type", constant = "SHARED")
	@Mapping(target = "defaultName", source = "request.calendarName")
	@Mapping(target = "calendarId", expression = "java(IdUtil.simpleUUID())")
	@Mapping(target = "userId", expression = "java(SecurityUtil.getUserId())")
	@Mapping(target = "defaultColor", expression = "java(IBaseEnum.getByCode(CalendarColor.class, request.calendarColor()))")
	@Mapping(target = "visibility", expression = "java(IBaseEnum.getByCode(CalendarVisibility.class, request.visibility()))")
	CalendarCommand toCommand(SharedCalendarCreateRequest request);


	CalendarEntity toEntity(CalendarCommand command);

	CalendarDomain toDomain(CalendarEntity calendarEntity);

	CalendarDomain toDomain(CalendarJO calendarJO);

	CalendarResponse toResponse(CalendarDomain calendarDomain);
}
