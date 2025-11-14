package io.github.luyang.business.plan._common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarPermissionsEnum implements IBaseEnum<String> {

	BUSY_FREE("BUSY_FREE", "忙闲"),
	VIEW_DETAILS("VIEW_DETAILS", "查看详情"),
	EDIT("EDIT", "编辑"),
	ADMIN("ADMIN", "管理"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
