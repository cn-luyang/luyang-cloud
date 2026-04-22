package io.github.luyang.platform.uac.common.enums;

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

	USER_INFO("user:info:", Duration.ofSeconds(3600), "用户信息缓存，后缀为 userId"),
	AUTH_RSA_PRIVATE("auth:rsa:pri:", Duration.ofSeconds(600), "登录/注册RSA私钥，后缀为uuid/设备标识")
	;

	private final String prefix;
	private final Duration timeout;
	private final String desc;

	/**
	 * 拼接完整的 Redis Key
	 * @param suffix 动态后缀 (如 ID, Token 等)
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
