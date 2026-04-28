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

	AUTH_TGC("auth:tgc:", Duration.ofHours(2), "SSO全局登录态TGC"),
	AUTHORIZE_REQUEST_PARAMS("auth:authorize_request_params:", Duration.ofMinutes(5), "授权接口请求参数缓存"),

	ACCESS_TOKEN("access_token:", Duration.ofSeconds(3600), "Access Token信息缓存"),
	REFRESH_TOKEN("refresh_token", Duration.ofDays(7), "刷新令牌"),

	CLIENT_INFO("client:info:", Duration.ofSeconds(3600), "客户端信息缓存，后缀为 clientId"),
	;

	private final String prefix;
	private final Duration timeout;
	private final String desc;

	/**
	 * 拼接完整的 Redis Key
	 *
	 * @param suffix 动态后缀
	 * @return 完整的 Key 字符串
	 */
	public String of(String suffix) {
		return this.prefix + suffix;
	}

	/**
	 * 获取不需要后缀的静态 Key
	 */
	public String getKey() {
		return this.prefix;
	}
}
