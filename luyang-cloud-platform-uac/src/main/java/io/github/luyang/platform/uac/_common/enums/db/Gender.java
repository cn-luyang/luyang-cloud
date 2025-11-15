package io.github.luyang.platform.uac._common.enums.db;

import io.github.luyang.starter.base.common.enums.IBaseEnum;
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
public enum Gender implements IBaseEnum<String> {

	SECRET("SECRET", "保密"),
	MALE("MALE", "男"),
	FEMALE("FEMALE", "女")
	;

	private final String code;
	private final String message;
}
