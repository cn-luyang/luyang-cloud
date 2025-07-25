package io.github.luyang.business.jalendar.base.converter;

import io.github.luyang.business.jalendar.base.bean.CalendarSharedUser;
import io.github.luyang.business.jalendar.calendar.controller.request.CalendarSubscribeRequest;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;
import io.github.luyang.business.jalendar.calendar.repository.entity.SubscribeEntity;
import io.github.luyang.starter.security.util.SecurityUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 日历订阅对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {SecurityUtil.class})
public interface SubscribeConverter {

	@Mapping(target = "userId", expression = "java(SecurityUtil.getUserId())")
	SubscribeCommand toCommand(CalendarSubscribeRequest request);

	SubscribeEntity toEntity(SubscribeCommand command);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", constant = "ADMIN")
	@Mapping(target = "displayName", source = "command.defaultName")
	@Mapping(target = "displayColor", source = "command.defaultColor")
	@Mapping(target = "displayed", constant = "true")
	SubscribeEntity toOwnerSubscribeEntity(CalendarCommand command);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permissions", ignore = true)
	@Mapping(target = "userId", source = "sharedUser.userId")
	@Mapping(target = "calendarId", source = "command.calendarId")
//	@Mapping(target = "permissions", source = "sharedUser.permissions")
	@Mapping(target = "displayName", source = "command.defaultName")
	@Mapping(target = "displayColor", source = "command.defaultColor")
	@Mapping(target = "displayed", constant = "true")
	SubscribeEntity toSharedSubscribeEntity(CalendarCommand command, CalendarSharedUser sharedUser);
}
