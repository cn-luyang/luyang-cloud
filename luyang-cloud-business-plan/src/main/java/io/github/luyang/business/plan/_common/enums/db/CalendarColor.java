package io.github.luyang.business.plan._common.enums.db;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日历颜色枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarColor implements IBaseEnum<String> {

	RED("#FF0000", "红色"),
	ORANGE("#FFA500", "橙色"),
	YELLOW("#FFFF00", "黄色"),
	GREEN("#00FF00", "绿色"),
	BLUE("#0000FF", "蓝色"),
	INDIGO("#4B0082", "靛蓝"),
	PURPLE("#800080", "紫色"),
	PINK("#FFC0CB", "粉色"),
	BROWN("#964B00", "棕色"),
	GRAY("#808080", "灰色"),
	;

	@EnumValue
	private final String code;
	private final String message;
}
