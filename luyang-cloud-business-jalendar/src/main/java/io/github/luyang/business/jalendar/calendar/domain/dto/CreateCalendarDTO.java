package io.github.luyang.business.jalendar.calendar.domain.dto;

public record CreateCalendarDTO(String calendarId) {

	public static CreateCalendarDTO build(String calendarId) {
		return new CreateCalendarDTO(calendarId);
	}
}
