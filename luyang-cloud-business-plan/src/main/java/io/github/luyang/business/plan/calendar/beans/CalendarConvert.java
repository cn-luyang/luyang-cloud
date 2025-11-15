package io.github.luyang.business.plan.calendar.beans;

import cn.hutool.core.util.IdUtil;
import io.github.luyang.business.plan._common.enums.db.CalendarType;
import io.github.luyang.business.plan._common.util.ColorUtil;
import io.github.luyang.business.plan.calendar.beans.body.CalendarCreateRequest;
import io.github.luyang.business.plan.calendar.beans.entity.CalendarEntity;
import io.github.luyang.business.plan.subscribe.beans.bo.InitSubscribeParam;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author yang.lu
 */
@Mapper(
	componentModel = "spring",
	imports = {
		IdUtil.class,
		IBaseEnum.class,
		CalendarType.class,
		ColorUtil.class
	}
)
public interface CalendarConvert {

	@Mapping(target = "ownerUserId", expression = "java(IdUtil.simpleUUID())")
	@Mapping(target = "calendarType", expression = "java(CalendarType.SHARED)")
	@Mapping(target = "defaultName", source = "calendarCreateRequest.calendarName")
	@Mapping(target = "defaultColor", expression = "java(ColorUtil.findClosestColor(calendarCreateRequest.calendarColor()))")
	@Mapping(target = "calendarVisibility", expression = "java(IBaseEnum.getByCode(CalendarVisibility.class, calendarCreateRequest.calendarVisibility()))")
	CalendarEntity buildEntity(String calendarId, CalendarCreateRequest calendarCreateRequest);

	@Mapping(target = "userId", source = "calendarEntity.ownerUserId")
	@Mapping(target = "displayName", source = "calendarEntity.defaultName")
	@Mapping(target = "displayColor", source = "calendarEntity.defaultColor")
	InitSubscribeParam buildInitSubscribeParam(String calendarId, CalendarEntity calendarEntity);

	CalendarDomain buildDomain(CalendarEntity calendarEntity);
}
