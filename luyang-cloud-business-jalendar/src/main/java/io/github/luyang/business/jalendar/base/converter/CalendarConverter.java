package io.github.luyang.business.jalendar.base.converter;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarType;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;
import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.dto.GetCalendarSummaryDTO;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarEntity;
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
	@Mapping(target = "defaultName", source = "name")
	@Mapping(target = "calendarId", expression = "java(IdUtil.nanoId())")
	@Mapping(target = "userId", expression = "java(SecurityUtil.getUserId())")
	@Mapping(target = "visibility", expression = "java(IBaseEnum.getByCode(CalendarVisibility.class, command.visibility()))")
	@Mapping(target = "defaultColor", expression = "java(IBaseEnum.getByCode(CalendarColor.class, command.color()))")

	CalendarEntity buildEntity(CreateCalendarCommand command);


	GetCalendarSummaryDTO buildGetCalendarSummaryDTO(CalendarEntity calendarEntity);
}
