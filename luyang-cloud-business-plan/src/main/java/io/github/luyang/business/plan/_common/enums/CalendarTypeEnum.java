package io.github.luyang.business.plan._common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarTypeEnum implements IBaseEnum<String> {

	MAIN("MAIN", "主日历"),
	SHARED("SHARED", "共享日历"),
	GLOBAL("GLOBAL", "全员日历"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
