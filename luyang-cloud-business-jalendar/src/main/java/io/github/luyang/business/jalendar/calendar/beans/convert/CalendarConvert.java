package io.github.luyang.business.jalendar.calendar.beans.convert;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarType;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;
import io.github.luyang.business.jalendar.calendar.beans.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarEntity;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.security.util.SecurityUtil;

/**
 * 日历对象转换类
 *
 * @author yang.lu
 */
public class CalendarConvert {

	public static CalendarEntity buildEntity(String calendarId, CalendarCreateRequest calendarCreateRequest) {

		if (StrUtil.isEmpty(calendarId)) return null;
		if (BeanUtil.isEmpty(calendarCreateRequest)) return null;

		CalendarEntity calendarEntity = new CalendarEntity();
		calendarEntity.setId(calendarId);
		calendarEntity.setOwnerUserId(SecurityUtil.getUserId());
		calendarEntity.setDefaultName(calendarCreateRequest.calendarName());
		calendarEntity.setDefaultColor(IBaseEnum.getByCode(CalendarColor.class, calendarCreateRequest.calendarColor()));
		calendarEntity.setType(CalendarType.SHARED);
		calendarEntity.setVisibility(IBaseEnum.getByCode(CalendarVisibility.class, calendarCreateRequest.visibility()));
		calendarEntity.setDescription(calendarCreateRequest.description());
		return calendarEntity;
	}

	public static CalendarDomain buildDomain(CalendarEntity calendarEntity) {
		if (BeanUtil.isEmpty(calendarEntity)) return null;
		return new CalendarDomain(
			calendarEntity.getId(),
			calendarEntity.getDefaultName(),
			calendarEntity.getDefaultColor(),
			calendarEntity.getVisibility()
		);
	}
}
