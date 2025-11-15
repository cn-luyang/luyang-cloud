package io.github.luyang.business.plan.calendar.beans;

import io.github.luyang.business.plan._common.enums.db.CalendarVisibility;

/**
 * @param calendarId 日历ID
 * @param visibility 日历公开范围 {@link CalendarVisibility}
 * @author yang.lu
 */
public record CalendarDomain(
	String calendarId,
	CalendarVisibility visibility
) {

	public boolean isPrivate() {
		return this.visibility == CalendarVisibility.PRIVATE;
	}
}
