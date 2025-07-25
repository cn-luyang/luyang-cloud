package io.github.luyang.business.jalendar.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarPermission implements IBaseEnum<Integer> {

	BUSY_FREE(1, "忙闲"),
	VIEW_DETAILS(2, "查看详情"),
	EDIT(3, "编辑"),
	ADMIN(4, "管理"),
	;

	@EnumValue
	private final Integer code;
	private final String message;
}
