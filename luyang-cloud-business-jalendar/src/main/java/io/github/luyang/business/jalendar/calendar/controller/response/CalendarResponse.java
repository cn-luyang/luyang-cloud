package io.github.luyang.business.jalendar.calendar.controller.response;

/**
 * 日历响应
 *
 * @param calendarId   日历ID
 * @param defaultName  日历默认名称，创建时的名称
 * @param defaultColor 日历默认颜色，创建时的颜色
 * @param type         日历类型，[1:主日历] [2:共享日历] [3:全员日历]
 * @param visibility   日历公开范围，[1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]
 * @param description  日历描述
 * @param permissions  日历权限，对于当前身份
 * @param displayName  日历显示名称，对于当前身份
 * @param displayColor 日历显示颜色，对于当前身份
 * @param displayed    是否显示日历，对于当前身份
 * @author yang.lu
 */
public record CalendarResponse(
	String calendarId,
	String defaultName,
	String defaultColor,
	String type,
	String visibility,
	String description,
	String permissions,
	String displayName,
	String displayColor,
	Boolean displayed
) {
}
