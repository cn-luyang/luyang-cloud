package io.github.luyang.business.jalendar.base.enums;

import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CalendarType implements IBaseEnum<Integer> {

	PRIMARY(1, "主日历"),
	SHARE(2, "共享日历"),;

	private final Integer code;
	private final String message;
}
