package io.github.luyang.business.jalendar.calendar.domain.dto;

/**
 * @author yang.lu
 */
public record GetCalendarSummaryDTO(
	String calendarId,
	String defaultName,
	Integer visibility
) {
}
