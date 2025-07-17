package io.github.luyang.business.jalendar.calendar.controller.request;

/**
 * 日历订阅请求
 *
 * @param calendarId 被订阅日历ID，非空
 * @author yang.lu
 */
public record CalendarSubscribeRequest(

	String calendarId
) {
}
