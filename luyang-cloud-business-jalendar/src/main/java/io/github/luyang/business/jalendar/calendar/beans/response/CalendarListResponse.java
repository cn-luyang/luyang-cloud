package io.github.luyang.business.jalendar.calendar.beans.response;

/**
 * 日历列表响应对象
 *
 * @param calendarId   日历ID
 * @param defaultName  日历默认名称
 * @param defaultColor 日历默认颜色
 * @param displayName  日历显示名称 (对于当前身份)
 * @param displayColor 日历显示颜色 (对于当前身份)
 * @param calendarType 日历类型
 * @param selected     是否勾选 (对于当前身份)
 * @author yang.lu
 */
public record CalendarListResponse(
	String calendarId,
	String defaultName,
	String defaultColor,
	String displayName,
	String displayColor,
	String calendarType,
	Boolean selected
) {
}
