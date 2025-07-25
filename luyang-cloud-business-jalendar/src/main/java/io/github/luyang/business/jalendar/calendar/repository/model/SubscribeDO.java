package io.github.luyang.business.jalendar.calendar.repository.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermission;
import io.github.luyang.starter.mybatis.beans.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 日历数据库实体对象
 *
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "calendar_subscribe", autoResultMap = true)
public class SubscribeDO extends BaseEntity<SubscribeDO, Long> {

	private String userId;
	private String calendarId;
	private CalendarPermission permission;
	private String displayName;
	private CalendarColor displayColor;
	private Boolean displayed;
}
