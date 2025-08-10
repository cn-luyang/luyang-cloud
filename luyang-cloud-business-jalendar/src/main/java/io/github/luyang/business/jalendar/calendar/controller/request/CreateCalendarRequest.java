package io.github.luyang.business.jalendar.calendar.controller.request;

import java.util.List;

/**
 * 创建日历请求
 *
 * @param name,       日历名称，非空，最大长度 16
 * @param color       日历颜色，十六进制表示
 * @param visibility  日历公开范围 [1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]
 * @param description 日历描述，最多 256 字符
 * @param sharedUsers 共享用户列表
 * @author yang.lu
 */
public record CreateCalendarRequest(
        String name,
        String color,
        Integer visibility,
        String description,
        List<SharedUser> sharedUsers
) {

    /**
     * 共享用户列表
     *
     * @param userId,     用户ID
     * @param permissions 日历权限 [1:忙闲 2:查看详情 3:编辑 4:管理]
     * @author yang.lu
     */
    public record SharedUser(
            String userId,
            Integer permissions
    ) {
    }
}
