package io.github.luyang.business.plan._common.enums.error;

import io.github.luyang.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarError implements ExceptionAssert<String> {

	INCOMPLETE_CALENDAR("calendar:incomplete_calendar", "日历数据不完整"),
	INVALID_CALENDAR("calendar:invalid_calendar", "无效日历"),
	NOT_FOUND_CALENDAR("calendar:not_found_calendar", "未找到日历信息"),
	NOT_ALLOWED_SUBSCRIBE("calendar:not_allowed_subscribe", "不允许订阅"),
	REPEAT_SUBSCRIBE("calendar:repeat_subscribe", "重复订阅"),
	;

	private final String code;
	private final String message;
}
