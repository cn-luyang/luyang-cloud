package io.github.luyang.business.plan.subscribe.beans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.luyang.business.plan._common.enums.db.CalendarColor;
import io.github.luyang.business.plan._common.enums.db.CalendarPermissions;
import io.github.luyang.starter.mybatis.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yang.lu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "calendar_subscribe", autoResultMap = true)
public class CalendarSubscribeEntity extends BaseEntity {

	@TableId(type = IdType.ASSIGN_ID)
	private String subscribeId;

	/** 日历订阅者用户ID */
	private String userId;

	/** 日历ID */
	private String calendarId;

	/** 订阅者对于日历的权限 [BUSY_FREE:忙闲] [VIEW_DETAILS:查看详情] [EDIT:编辑] [ADMIN:管理] */
	private CalendarPermissions permissions;

	/** 日历名称(对于当前身份) */
	private String displayName;

	/** 日历颜色(对于当前身份) */
	private CalendarColor displayColor;

	/** 是否勾选日历(对于当前身份) [1:显示:true] [0:隐藏:false] */
	private Boolean selected;

	/** 是否取消订阅(对于当前身份) [1:是:true] [0:否:false] */
	private Boolean unsubscribe;
}
