package io.github.luyang.business.jalendar.calendar.controller.response;

/**
 * 日历响应 (Response)
 * 用于向客户端返回日历信息
 *
 * @param calendarId 日历ID
 * @author yang.lu
 */
public record CalendarResponse(
	String calendarId
) {
}
