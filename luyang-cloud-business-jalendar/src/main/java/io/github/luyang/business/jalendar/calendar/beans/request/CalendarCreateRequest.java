package io.github.luyang.business.jalendar.calendar.beans.request;

import java.util.List;

/**
 * 日历创建请求对象
 *
 * @author yang.lu
 */
public record CalendarCreateRequest(
	String calendarName,
	String calendarColor,
	String visibility,
	String description,
	List<SharedUser> sharedUsers
) {

	/**
	 * 共享用户列表
	 *
	 * @param userId,     用户ID
	 * @param permissions 日历权限
	 * @author yang.lu
	 */
	public record SharedUser(
		String userId,
		String permissions
	) {
	}
}
