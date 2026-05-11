package io.github.luyang.api.uac.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账号类型枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountTypeEnum {

	USER_NAME, PHONE, EMAIL
}
