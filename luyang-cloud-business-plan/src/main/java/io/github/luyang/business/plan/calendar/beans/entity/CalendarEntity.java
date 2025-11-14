package io.github.luyang.business.plan.calendar.beans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "calendar", autoResultMap = true)
public class CalendarEntity extends BaseEntity {

	/** 日历ID */
	private String calendarId;

	/** 日历拥有者用户ID */
	private String ownerUserId;

	/** 日历默认名称，创建时的名称 */
	private String defaultName;

	/** 日历默认颜色，创建时的颜色 */
//	private CalendarColor defaultColor;

	/** 日历类型 {[1:主日历] [2:共享日历] [3:全员日历]} */
//	private CalendarType type;

	/** 日历公开范围 {[1:私密-不可自行订阅] [2:简览-可订阅，仅忙闲] [3:公开-可订阅，查看日程]} */
//	private CalendarVisibility visibility;

	/** 日历描述 */
	private String description;
}
