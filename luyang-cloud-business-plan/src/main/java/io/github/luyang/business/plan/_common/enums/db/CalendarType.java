package io.github.luyang.business.plan._common.enums.db;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日历类型枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarType implements IBaseEnum<String> {

	MAIN("MAIN", "主日历"),
	SHARED("SHARED", "共享日历"),
	GLOBAL("GLOBAL", "全员日历"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
