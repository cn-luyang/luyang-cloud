package io.github.luyang.platform.uac.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender {

	CONFIDENTIAL(0, "保密"),
	MALE(1, "男"),
	FEMALE(2, "女"),

	;
	private final Integer code;
	private final String message;
}
