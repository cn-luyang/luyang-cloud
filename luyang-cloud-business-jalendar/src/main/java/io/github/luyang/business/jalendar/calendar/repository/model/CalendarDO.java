package io.github.luyang.business.jalendar.calendar.repository.model;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "calendar", autoResultMap = true)
public class CalendarDO extends BaseEntity<CalendarDO, Long> {

	private String calendarId;
	private String userId;
	private String calendarName;
	private String defaultColor;
	private Integer calendarType;
	private Integer description;
}
