package io.github.luyang.business.jalendar.calendar.service.command;

import cn.hutool.core.collection.CollUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;
import io.github.luyang.business.jalendar.base.enums.CalendarType;
import io.github.luyang.business.jalendar.base.enums.CalendarVisibility;

import java.util.Collections;
import java.util.List;

/**
 * 日历命令对象 (Command)
 * 用于在 Service 层内部传递创建或更新操作的数据
 * 与外部请求DTO解耦，允许Service层内部数据结构的变化不影响Controller层
 *
 * @param calendarId   日历ID
 * @param defaultName  日历默认名称，创建时的名称
 * @param defaultColor 日历默认颜色，创建时的颜色
 * @param type         日历类型，[1:主日历] [2:共享日历] [3:全员日历]
 * @param visibility   日历公开范围，[1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]
 * @param description  日历描述
 * @param sharedUsers  共享用户列表
 * @author yang.lu
 */
public record CalendarCommand(
        String calendarId,
        String userId,
        String defaultName,
        CalendarColor defaultColor,
        CalendarType type,
        CalendarVisibility visibility,
        String description,
        List<SharedUser> sharedUsers
) {

    public CalendarCommand {
        if (CollUtil.isEmpty(sharedUsers)) {
            sharedUsers = Collections.emptyList();
        }
    }

    /**
     * 日历共享用户
     *
     * @param userId      用户ID
     * @param permissions 日历权限 [1:忙闲 2:查看详情 3:编辑 4:管理]
     */
    public record SharedUser(
            String userId,
            CalendarPermissions permissions
    ) {
    }
}
