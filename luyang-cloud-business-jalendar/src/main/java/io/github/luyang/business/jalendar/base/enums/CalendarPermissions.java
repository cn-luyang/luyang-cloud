package io.github.luyang.business.jalendar.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarPermissions implements IBaseEnum<String> {

	BUSY_FREE("BUSY_FREE", "忙闲"),
	VIEW_DETAILS("VIEW_DETAILS", "查看详情"),
	EDIT("EDIT", "编辑"),
	ADMIN("ADMIN", "管理"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
