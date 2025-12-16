package io.github.luyang.platform.uac._common.enums.db;

import io.github.luyang.starter.base.enums.IBaseEnum;
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
public enum Gender implements IBaseEnum<Integer> {

	MALE(1, "男性"),
	FEMALE(2, "女性"),
	;

	private final Integer code;
	private final String message;
}
