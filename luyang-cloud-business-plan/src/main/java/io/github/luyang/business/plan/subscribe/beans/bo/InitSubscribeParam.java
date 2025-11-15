package io.github.luyang.business.plan.subscribe.beans.bo;

import io.github.luyang.business.plan._common.enums.db.CalendarColor;

/**
 * 初始订阅日历入参
 *
 * @param userId       日历订阅者用户ID
 * @param calendarId   日历ID
 * @param displayName  日历名称(对于当前身份)
 * @param displayColor 日历颜色(对于当前身份)
 * @author yang.lu
 */
public record InitSubscribeParam(
	String userId,
	String calendarId,
	String displayName,
	CalendarColor displayColor
) {
}
