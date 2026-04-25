package io.github.luyang.platform.uaa.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

/**
 * 缓存 Key 定义
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CacheKey {

	ACCESS_TOKEN("auth:access_token:", Duration.ofSeconds(3600), "Access Token信息缓存"),
	;

	private final String prefix;
	private final Duration timeout;
	private final String desc;

	/**
	 * 拼接完整的 Redis Key
	 * @param suffix 动态后缀
	 * @return 完整的 Key 字符串
	 */
	public String getKey(Object suffix) {
		return this.prefix + suffix.toString();
	}

	/**
	 * 获取不需要后缀的静态 Key
	 */
	public String getKey() {
		return this.prefix;
	}
}
