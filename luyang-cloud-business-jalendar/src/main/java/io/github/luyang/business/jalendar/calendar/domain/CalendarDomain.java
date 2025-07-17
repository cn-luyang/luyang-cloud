package io.github.luyang.business.jalendar.calendar.domain;

import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;
import io.github.luyang.business.jalendar.base.enums.error.CalendarError;
import io.github.luyang.starter.base.error.BusinessException;

/**
 * 日历业务领域对象
 * 封装日历的核心业务属性和可能的业务方法
 *
 * @author yang.lu
 */

public record CalendarDomain(
	CalendarVisibility visibility
) {


	/**
	 * 检查当前日历是否为私密日历
	 *
	 * @return true表示是私密日历，false表示是公开日历
	 * @throws BusinessException 当visibility为null时抛出
	 * @author yang.lu
	 */
	public boolean isPrivateCalendar() {
		CalendarError.INCOMPLETE_CALENDAR.notNull(visibility);
		return CalendarVisibility.PRIVATE == visibility;
	}
}
