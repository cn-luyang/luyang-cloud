package io.github.luyang.business.jalendar.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarVisibility implements IBaseEnum<Integer> {

	PRIVATE(1, "私密（不可订阅）"),
	GUEST(2, "简览（可订阅，仅忙闲）"),
	SHARED(3, "公开（可订阅，查看日程）"),
	;

	@EnumValue
	private final Integer code;
	private final String message;
}
