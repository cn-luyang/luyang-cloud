package io.github.luyang.business.jalendar.calendar.converter;

import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;
import io.github.luyang.business.jalendar.calendar.repository.entity.CalendarSubscribeEntity;
import io.github.luyang.business.jalendar.calendar.service.command.CalendarCommand;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.security.util.SecurityUtil;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Stream;

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

	default List<CalendarSubscribeEntity> buildEntity(CalendarCommand command) {

		return Stream.concat(
			Stream.of(new CalendarCommand.SharedUser(command.userId(), CalendarPermissions.ADMIN)),
			command.sharedUsers().stream()
		).map(sharedUser -> {
			CalendarSubscribeEntity calendarSubscribeEntity = new CalendarSubscribeEntity();
			calendarSubscribeEntity.setUserId(sharedUser.userId());
			calendarSubscribeEntity.setCalendarId(command.calendarId());
			calendarSubscribeEntity.setPermissions(sharedUser.permissions());
			calendarSubscribeEntity.setDisplayName(command.defaultName());
			calendarSubscribeEntity.setDisplayColor(command.defaultColor());
			return calendarSubscribeEntity;
		}).toList();
	}
}
