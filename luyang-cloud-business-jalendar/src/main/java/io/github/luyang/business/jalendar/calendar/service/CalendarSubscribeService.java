package io.github.luyang.business.jalendar.calendar.service;

import io.github.luyang.business.jalendar.calendar.beans.request.CalendarCreateRequest;
import io.github.luyang.business.jalendar.calendar.beans.request.CalendarSubscribeRequest;

/**
 * 日历订阅服务接口
 *
 * @author yang.lu
 */
public interface CalendarSubscribeService {

	/**
	 * 初始化日历订阅（创建日历时自动订阅）
	 *
	 * @param calendarId            日历ID
	 * @param calendarCreateRequest 日历创建请求
	 * @author yang.lu
	 */
	void initSubscribe(String calendarId, CalendarCreateRequest calendarCreateRequest);

	/**
	 * 用户主动订阅日历
	 *
	 * @param request 日历订阅请求
	 * @author yang.lu
	 */
	void subscribeCalendar(CalendarSubscribeRequest request);
}
