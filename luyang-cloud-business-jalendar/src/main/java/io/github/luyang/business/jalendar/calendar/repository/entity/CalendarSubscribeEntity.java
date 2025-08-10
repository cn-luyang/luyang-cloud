package io.github.luyang.business.jalendar.calendar.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.business.jalendar.base.enums.CalendarColor;
import io.github.luyang.business.jalendar.base.enums.CalendarPermissions;
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
public class CalendarSubscribeEntity extends BaseEntity<CalendarSubscribeEntity, Long> {

	/** 日历订阅者用户ID */
	private String userId;

	/** 日历ID */
	private String calendarId;

	/** 订阅者对于日历的权限: {[1:忙闲] [2:查看详情] [3:编辑] [4:管理]} */
	private CalendarPermissions permissions;

	/** 日历名称(对于当前身份) */
	private String displayName;

	/** 日历颜色(对于当前身份) */
	private CalendarColor displayColor;

	/** 是否显示日历(对于当前身份): {[1:显示:true] [0:隐藏:false]} */
	private Boolean displayed;

	/** 是否取消订阅(对于当前身份): {[1:是:true] [0:否:false]} */
	private Boolean unsubscribe;
}
