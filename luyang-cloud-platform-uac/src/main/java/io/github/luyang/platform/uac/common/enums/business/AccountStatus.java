package io.github.luyang.platform.uac.common.enums.business;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.github.luyang.starter.base.enums.IBaseEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountStatus implements IBaseEnum<Integer> {

	UNACTIVATED(1, "未激活"),
	NORMAL(2, "激活"),
	;

	@EnumValue
	private final Integer code;
	private final String message;
}
