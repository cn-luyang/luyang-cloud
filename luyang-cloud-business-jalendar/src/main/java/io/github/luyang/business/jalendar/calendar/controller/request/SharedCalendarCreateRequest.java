package io.github.luyang.business.jalendar.calendar.controller.request;

import io.github.luyang.business.jalendar.base.bean.CalendarSharedUser;

import java.util.List;

/**
 * 创建共享日历请求
 *
 * @param calendarName  日历名称，非空，最大长度 64
 * @param calendarColor 日历颜色，十六进制表示
 * @param visibility    日历公开范围 [1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]
 * @param description   日历描述
 * @param sharedUsers   共享用户列表
 * @author yang.lu
 */
public record SharedCalendarCreateRequest(

	String calendarName,
	String calendarColor,
	Integer visibility,
	String description,
	List<CalendarSharedUser> sharedUsers
) {
}
