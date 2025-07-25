package io.github.luyang.business.jalendar.base.converter;

import io.github.luyang.business.jalendar.base.bean.CalendarSharedUser;
import io.github.luyang.business.jalendar.calendar.controller.request.CalendarSubscribeRequest;
import io.github.luyang.business.jalendar.calendar.domain.command.CalendarCommand;
import io.github.luyang.business.jalendar.calendar.domain.command.SubscribeCommand;
import io.github.luyang.business.jalendar.calendar.repository.model.SubscribeDO;
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

	SubscribeDO toDO(SubscribeCommand command);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "permission", constant = "ADMIN")
	@Mapping(target = "displayName", source = "command.calendarName")
	@Mapping(target = "displayColor", source = "command.calendarColor")
	@Mapping(target = "displayed", constant = "true")
	SubscribeDO toOwnerSubscribeDO(CalendarCommand command);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "userId", source = "sharedUser.userId")
	@Mapping(target = "calendarId", source = "command.calendarId")
	@Mapping(target = "permission", source = "sharedUser.permission")
	@Mapping(target = "displayName", source = "command.calendarName")
	@Mapping(target = "displayColor", source = "command.calendarColor")
	@Mapping(target = "displayed", constant = "true")
	SubscribeDO toSharedSubscribeDO(CalendarCommand command, CalendarSharedUser sharedUser);
}
