package io.github.luyang.platform.uaa._common.enums.infra;

import io.github.luyang.starter.redisson.IRedisKeyEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

/**
 * Redis 缓存 Key 定义
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RedisKey implements IRedisKeyEnum {

	LOGIN_TICKET("login_ticket", Duration.ofSeconds(30), "登录流程临时凭证"),
	SSO_SID("sso_sid", Duration.ofHours(2), "SSO 全局会话 ID"),
	AUTHORIZATION_CODE("authorization_code", Duration.ofMinutes(3), "授权码信息"),
	CLIENT_DETAILS("client_details", Duration.ofHours(8), "客户端信息"),
	;

	private final String prefix;
	private final Duration ttl;
	private final String desc;
}
