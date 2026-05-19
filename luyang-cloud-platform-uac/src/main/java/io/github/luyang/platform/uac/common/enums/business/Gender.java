package io.github.luyang.platform.uac.common.enums.business;

import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender implements IBaseEnum<Integer> {

	UNKNOWN(0, "保密"),
	MALE(1, "男"),
	FEMALE(2, "女"),
	;
	private final Integer code;
	private final String message;
}
