package io.github.luyang.business.jalendar.calendar.beans.domain;

import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;

public record CalendarDomain(
	String calendarId,
	String defaultName,
	CalendarColor defaultColor,
	CalendarVisibility visibility
) {

	public boolean isPrivate() {
		return this.visibility == CalendarVisibility.PRIVATE;
	}

	public CalendarPermissions subscribePermissions() {
		return switch (this.visibility) {
			case GUEST -> CalendarPermissions.BUSY_FREE;
			case PUBLIC -> CalendarPermissions.VIEW_DETAILS;
			default -> throw new IllegalStateException("Unsupported visibility: " + this.visibility);
		};
	}
}
