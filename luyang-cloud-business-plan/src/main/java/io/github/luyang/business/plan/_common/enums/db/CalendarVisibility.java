package io.github.luyang.business.plan._common.enums.db;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日历公开范围枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarVisibility implements IBaseEnum<String> {

	PRIVATE("PRIVATE", "私密（不可订阅）"),
	GUEST("GUEST", "简览（可订阅，仅忙闲）"),
	PUBLIC("PUBLIC", "公开（可订阅，查看日程）"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
