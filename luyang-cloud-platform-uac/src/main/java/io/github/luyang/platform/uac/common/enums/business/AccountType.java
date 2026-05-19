package io.github.luyang.platform.uac.common.enums.business;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountType implements IBaseEnum<Integer> {

	USER_NAME(1, "用户名"),
	MOBILE(2, "手机号"),
	EMAIL(2, "邮箱号"),
	;

	@EnumValue
	private final Integer code;
	private final String message;
}
