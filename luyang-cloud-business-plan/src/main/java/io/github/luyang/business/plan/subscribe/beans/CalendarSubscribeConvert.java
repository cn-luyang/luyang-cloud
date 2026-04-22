package io.github.luyang.business.plan.subscribe.beans;

import io.github.luyang.business.plan._common.enums.db.CalendarPermissions;
import io.github.luyang.business.plan.calendar.beans.CalendarDomain;
import io.github.luyang.business.plan.subscribe.beans.bo.InitSubscribeParam;
import io.github.luyang.business.plan.subscribe.beans.entity.CalendarSubscribeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		Boolean.class,
		CalendarPermissions.class
	}
)
public interface CalendarSubscribeConvert {

	@Mapping(target = "selected", expression = "java(Boolean.TRUE)")
	@Mapping(target = "unsubscribe", expression = "java(Boolean.FALSE)")
	@Mapping(target = "permissions", expression = "java(CalendarPermissions.ADMIN)")
	CalendarSubscribeEntity buildEntity(InitSubscribeParam initSubscribeParam);

	CalendarSubscribeEntity buildEntity(String userId, CalendarDomain calendarDomain);
}
