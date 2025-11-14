package io.github.luyang.business.plan.calendar.beans.body;

/**
 * @author yang.lu
 */
public record CalendarCreateResponse(String calendarId) {

	public static CalendarCreateResponse build(String calendarId) {
		return new CalendarCreateResponse(calendarId);
	}
}
