package io.github.luyang.business.plan._common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarVisibilityEnum implements IBaseEnum<String> {

	PRIVATE("PRIVATE", "私密（不可订阅）"),
	GUEST("GUEST", "简览（可订阅，仅忙闲）"),
	PUBLIC("PUBLIC", "公开（可订阅，查看日程）"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
