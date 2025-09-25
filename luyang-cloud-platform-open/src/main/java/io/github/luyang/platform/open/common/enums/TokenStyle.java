package io.github.luyang.platform.open.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Token风格相关枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum TokenStyle {

	ACCESS_TOKEN, REFRESH_TOKEN, CLIENT_TOKEN
}
