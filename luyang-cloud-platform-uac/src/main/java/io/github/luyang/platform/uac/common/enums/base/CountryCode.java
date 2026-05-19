package io.github.luyang.platform.uac.common.enums.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 国家码
 *
 * @author yang.lu
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CountryCode {

	CHINA("CN", "+86", "中国"),
	JAPAN("JPN", "+81", "日本"),
	KOREA("KR", "+010", "韩国"),
	USA("USA", "+1", "美国"),
	;

	private final String code;
	private final String phoneCode;
	private final String countryName;
}
