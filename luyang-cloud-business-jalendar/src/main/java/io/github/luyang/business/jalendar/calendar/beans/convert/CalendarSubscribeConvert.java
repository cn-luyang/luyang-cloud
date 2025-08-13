package io.github.luyang.business.jalendar.calendar.beans.convert;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;
import io.github.luyang.business.jalendar.calendar.beans.domain.CalendarDomain;
import io.github.luyang.business.jalendar.calendar.beans.entity.CalendarSubscribeEntity;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;
import io.github.luyang.starter.base.enums.IBaseEnum;
import io.github.luyang.starter.security.util.SecurityUtil;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 日历订阅对象转换类
 *
 * @author yang.lu
 */
public class CalendarSubscribeConvert {

	public static List<CalendarSubscribeEntity> buildEntities(String calendarId, CalendarCreateRequest calendarCreateRequest) {

		if (StrUtil.isEmpty(calendarId)) return Collections.emptyList();
		if (BeanUtil.isEmpty(calendarCreateRequest)) return Collections.emptyList();

		return Stream.concat(
				Stream.of(new CalendarCreateRequest.SharedUser(SecurityUtil.getUserId(), CalendarPermissions.ADMIN.getCode())),
				calendarCreateRequest.sharedUsers().stream()
			).map(sharedUser -> {
				CalendarSubscribeEntity calendarSubscribeEntity = new CalendarSubscribeEntity();
				calendarSubscribeEntity.setUserId(sharedUser.userId());
				calendarSubscribeEntity.setCalendarId(calendarId);
				CalendarPermissions calendarPermissions = IBaseEnum.getByCode(CalendarPermissions.class, sharedUser.permissions());
				calendarSubscribeEntity.setPermissions(calendarPermissions);
				calendarSubscribeEntity.setDisplayName(calendarCreateRequest.calendarName());
				CalendarColor calendarColor = IBaseEnum.getByCode(CalendarColor.class, calendarCreateRequest.calendarColor());
				calendarSubscribeEntity.setDisplayColor(calendarColor);
				return calendarSubscribeEntity;
			}).collect(Collectors.toMap(
				CalendarSubscribeEntity::getUserId,
				Function.identity(),
				// 保留后出现的实体
				(existing, replacement) -> replacement
			))
			.values()
			.stream()
			.toList();
	}

	public static CalendarSubscribeEntity buildEntity(String userId, CalendarDomain calendarDomain) {
		if (StrUtil.isEmpty(userId)) return null;
		if (BeanUtil.isEmpty(calendarDomain)) return null;

		CalendarSubscribeEntity calendarSubscribeEntity = new CalendarSubscribeEntity();
		calendarSubscribeEntity.setUserId(userId);
		calendarSubscribeEntity.setCalendarId(calendarDomain.calendarId());
		calendarSubscribeEntity.setPermissions(calendarDomain.subscribePermissions());
		calendarSubscribeEntity.setDisplayName(calendarDomain.defaultName());
		calendarSubscribeEntity.setDisplayColor(calendarDomain.defaultColor());
		calendarSubscribeEntity.setSelected(true);
		calendarSubscribeEntity.setUnsubscribe(false);

		return calendarSubscribeEntity;
	}
}
