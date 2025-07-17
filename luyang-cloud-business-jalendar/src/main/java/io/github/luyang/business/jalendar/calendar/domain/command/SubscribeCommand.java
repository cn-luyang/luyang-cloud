package io.github.luyang.business.jalendar.calendar.domain.command;

import io.github.luyang.business.jalendar.base.enums.CalendarColor;

/**
 * 日历订阅命令对象
 *
 * @param userId       订阅用户ID
 * @param calendarId   被日历ID
 * @param displayName  日历显示名称
 * @param displayColor 日历显示颜色
 * @param displayed    是否显示日历
 * @author yang.lu
 */
public record SubscribeCommand(
	String userId,
	String calendarId,
	String displayName,
	CalendarColor displayColor,
	Boolean displayed
) {
}
