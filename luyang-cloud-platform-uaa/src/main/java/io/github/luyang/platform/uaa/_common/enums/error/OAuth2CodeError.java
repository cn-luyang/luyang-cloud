package io.github.luyang.platform.uaa._common.enums.error;


import io.github.luyang.starter.base.exception.ExceptionAssert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * OAuth2 业务错误码枚举
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum OAuth2CodeError implements ExceptionAssert<String> {

	CODE_SAVE_FAILED("oauth2_code:code_save_failed", "授权码入库保存失败"),
	;

	private final String code;
	private final String message;
}
