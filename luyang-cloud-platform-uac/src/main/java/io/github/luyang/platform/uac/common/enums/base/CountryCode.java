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

	CHINA_MAINLAND("CN", "+86", "中国大陆"),
	CHINA_HONGKONG("HK", "+852", "中国香港"),
	CHINA_MACAU("MO", "+853", "中国澳门"),
	CHINA_TAIWAN("TW", "+886", "中国台湾"),

	JAPAN("JP", "+81", "日本"),
	KOREA("KR", "+82", "韩国"),
	USA("US", "+1", "美国")
	;

	private final String code;
	private final String phoneCode;
	private final String countryName;
}
