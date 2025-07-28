package io.github.luyang.business.jalendar.base.converter;

import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;
import io.github.luyang.business.jalendar.calendar.domain.command.CreateCalendarCommand;
import io.github.luyang.business.jalendar.calendar.repository.entity.SubscribeEntity;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.security.util.SecurityUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * 日历订阅对象转换器
 *
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		SecurityUtil.class,
		IBaseEnum.class,
		CalendarColor.class})
public interface SubscribeConverter {

	@Mapping(target = "permissions", constant = "ADMIN")
	@Mapping(target = "calendarId", source = "calendarId")
	@Mapping(target = "displayName", source = "command.name")
	@Mapping(target = "userId", expression = "java(SecurityUtil.getUserId())")
	@Mapping(target = "displayColor", expression = "java(IBaseEnum.getByCode(CalendarColor.class, command.color()))")
	SubscribeEntity buildOwnerSubscribeEntity(String calendarId, CreateCalendarCommand command);

	default List<SubscribeEntity> buildSharedSubscribeEntity(String calendarId, CreateCalendarCommand command) {
		return command.sharedUsers().stream().map(sharedUser -> {
			SubscribeEntity subscribeEntity = new SubscribeEntity();
			subscribeEntity.setUserId(sharedUser.userId());
			subscribeEntity.setCalendarId(calendarId);
			subscribeEntity.setPermissions(IBaseEnum.getByCode(CalendarPermissions.class, sharedUser.permissions()));
			subscribeEntity.setDisplayName(command.name());
			subscribeEntity.setDisplayColor(IBaseEnum.getByCode(CalendarColor.class, command.color()));
			return subscribeEntity;
		}).toList();
	}
}
