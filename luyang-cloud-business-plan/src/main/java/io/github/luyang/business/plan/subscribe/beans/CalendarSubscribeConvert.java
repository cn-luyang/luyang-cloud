package io.github.luyang.business.plan.subscribe.beans;

import io.github.luyang.business.plan._common.enums.CalendarColorEnum;
import io.github.luyang.business.plan._common.enums.CalendarPermissionsEnum;
import io.github.luyang.business.plan.subscribe.beans.bo.InitSubscribeParam;
import io.github.luyang.business.plan.subscribe.beans.entity.CalendarSubscribeEntity;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import org.mapstruct.Mapper;

/**
 * @author yang.lu
 */
@Mapper(componentModel = "spring")
public interface CalendarSubscribeConvert {

	default CalendarSubscribeEntity buildEntity(InitSubscribeParam initSubscribeParam) {

		CalendarSubscribeEntity  entity = new CalendarSubscribeEntity();
		entity.setUserId("");
		entity.setCalendarId(initSubscribeParam.calendarId());
		entity.setPermissions(CalendarPermissionsEnum.ADMIN);
		entity.setDisplayName(initSubscribeParam.calendarName());

		CalendarColorEnum calendarColorEnum = IBaseEnum.getByProperty(
			CalendarColorEnum.class,
			CalendarColorEnum::getCode,
			initSubscribeParam.calendarColor()
		);

		entity.setDisplayColor(calendarColorEnum);
		entity.setSelected(Boolean.TRUE);
		entity.setUnsubscribe(Boolean.FALSE);

		return entity;
	}
}
