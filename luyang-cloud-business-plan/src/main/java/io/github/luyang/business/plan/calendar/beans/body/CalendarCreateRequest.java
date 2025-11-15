package io.github.luyang.business.plan.calendar.beans.body;

import io.github.luyang.business.plan._common.enums.db.CalendarColor;
import io.github.luyang.business.plan._common.enums.db.CalendarVisibility;

/**
 * 日历创建请求
 *
 * @param calendarName       日历名称，非空，最大长度 16
 * @param calendarColor      日历颜色，{@link CalendarColor}
 * @param calendarVisibility 日历公开范围，{@link CalendarVisibility}
 * @param description        日历描述，可选，最大长度 256
 * @author yang.lu
 */
public record CalendarCreateRequest(
	String calendarName,
	String calendarColor,
	String calendarVisibility,
	String description
) {
}
