package io.github.luyang.business.jalendar.calendar.domain;

/**
 * 日历命令对象 (Command)
 * 用于在 Service 层内部传递创建或更新操作的数据
 * 与外部请求DTO解耦，允许Service层内部数据结构的变化不影响Controller层
 *
 * @param id           主键ID (更新时使用)
 * @param calendarId   日历ID
 * @param userId       用户ID
 * @param calendarName 日历名称
 * @param defaultColor 默认显示颜色
 * @param calendarType 日历类型
 * @param description  日历描述
 * @author yang.lu
 */
public record CalendarCommand(
	Long id,
	String calendarId,
	String userId,
	String calendarName,
	String defaultColor,
	Integer calendarType,
	String description
) {
}
