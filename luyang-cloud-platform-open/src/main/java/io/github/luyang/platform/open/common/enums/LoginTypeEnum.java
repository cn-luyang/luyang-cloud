package io.github.luyang.platform.open.common.enums;


import io.github.luyang.starter.base.common.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录类型相关枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum LoginTypeEnum implements IBaseEnum<String> {

	PASSWORD("password", "密码登录");

	private final String code;
	private final String message;
}
