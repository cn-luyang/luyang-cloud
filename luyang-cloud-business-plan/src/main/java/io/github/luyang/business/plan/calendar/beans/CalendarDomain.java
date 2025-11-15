package io.github.luyang.business.plan.calendar.beans;

import io.github.luyang.business.plan._common.enums.db.CalendarVisibility;

/**
 * @param calendarId         日历ID
 * @param calendarVisibility 日历公开范围 {@link CalendarVisibility}
 * @author yang.lu
 */
public record CalendarDomain(
	String calendarId,
	CalendarVisibility calendarVisibility
) {

	public boolean isPrivate() {
		return this.calendarVisibility == CalendarVisibility.PRIVATE;
	}
}
