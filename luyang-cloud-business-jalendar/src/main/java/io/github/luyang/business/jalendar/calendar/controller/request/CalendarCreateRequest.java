package io.github.luyang.business.jalendar.calendar.controller.request;

/**
 * 创建日历请求 (Request)
 *
 * @param name        日历名称，非空
 * @param color       日历颜色，十六进制表示
 * @param visibility  日历公开范围 [1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]
 * @param description 日历描述
 * @author yang.lu
 */
public record CalendarCreateRequest(

	String name,
	String color,
	Integer visibility,
	String description
) {

	/**
	 * 共享用户信息
	 *
	 * @param userId     用户ID
	 * @param permission 权限 [1:只读 2:编辑 3:管理]
	 */
	public record SharedUser(
		String userId,
		Integer permission
	) {
	}
}
