package io.github.luyang.business.jalendar.calendar.controller.response;

public record CreateCalendarResponse(String calendarId) {
	public static CreateCalendarResponse build(String calendarId) {
		return new CreateCalendarResponse(calendarId);
	}
}
