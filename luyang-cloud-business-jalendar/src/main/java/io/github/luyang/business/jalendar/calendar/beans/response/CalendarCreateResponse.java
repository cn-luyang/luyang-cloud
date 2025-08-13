package io.github.luyang.business.jalendar.calendar.beans.response;

/**
 * 日历创建响应对象
 *
 * @param calendarId 日历ID
 * @author yang.lu
 */
public record CalendarCreateResponse(String calendarId) {

	public static CalendarCreateResponse build(String calendarId) {
		return new CalendarCreateResponse(calendarId);
	}
}
