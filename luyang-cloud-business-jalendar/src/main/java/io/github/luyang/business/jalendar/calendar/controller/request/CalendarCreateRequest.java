package io.github.luyang.business.jalendar.calendar.controller.request;

/**
 * 创建日历请求 (Request)
 *
 * @param calendarName 日历名称，非空
 * @param defaultColor 默认颜色，默认
 * @param description  日历描述
 * @author yang.lu
 */
public record CalendarCreateRequest(

	String calendarName,
	String defaultColor,
	String description
) {
}
