package io.github.luyang.business.jalendar.calendar.domain.command;

import cn.hutool.core.collection.CollUtil;
import io.github.luyang.business.jalendar.base.bean.CalendarSharedUser;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarType;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;

import java.util.Collections;
import java.util.List;

/**
 * 日历命令对象 (Command)
 * 用于在 Service 层内部传递创建或更新操作的数据
 * 与外部请求DTO解耦，允许Service层内部数据结构的变化不影响Controller层
 *
 * @param calendarId    日历ID
 * @param userId        用户ID
 * @param calendarName  日历名称
 * @param calendarColor 日历颜色
 * @param visibility    日历公开范围
 * @param calendarType  日历类型
 * @param description   日历描述
 * @param sharedUsers   共享用户列表
 * @author yang.lu
 */
public record CalendarCommand(
	String calendarId,
	String userId,
	String calendarName,
	CalendarColor calendarColor,
	CalendarType calendarType,
	CalendarVisibility visibility,
	String description,
	List<CalendarSharedUser> sharedUsers
	) {

	public CalendarCommand {
		if (CollUtil.isEmpty(sharedUsers)) {
			sharedUsers = Collections.emptyList();
		}
	}
}
