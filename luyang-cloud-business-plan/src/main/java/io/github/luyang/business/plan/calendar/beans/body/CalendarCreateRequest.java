package io.github.luyang.business.plan.calendar.beans.body;

/**
 * 日历创建请求
 *
 * @param calendarName  日历名称，非空，最大长度 16
 * @param calendarColor
 * @param visibility
 * @param description   日历描述，可选，最大长度 256
 * @author yang.lu
 */
public record CalendarCreateRequest(
	String calendarName,
	String calendarColor,
	String visibility,
	String description
) {
}
