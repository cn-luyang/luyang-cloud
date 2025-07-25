package io.github.luyang.business.jalendar.base.bean;

import cn.hutool.core.collection.CollUtil;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 日历共享用户
 *
 * @param userId     用户ID
 * @param permissions 日历权限 [1:忙闲 2:查看详情 3:编辑 4:管理]
 */

public record CalendarSharedUser(
	String userId,
	CalendarPermissions permissions
) {

	/**
	 * 根据 userId 去重，保留最后一个出现的元素
	 *
	 * @author yang.lu
	 */
	public static List<CalendarSharedUser> deduplicateByUserId(List<CalendarSharedUser> users) {
		if (CollUtil.isEmpty(users)) {
			return Collections.emptyList();
		}

		return users.stream()
			.collect(Collectors.toMap(
				CalendarSharedUser::userId,
				Function.identity(),
				(oldValue, newValue) -> newValue
			))
			.values()
			.stream()
			.toList();
	}
}
