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

	/**
	 * 登录流程临时凭证
	 * Key: platform-uaa:login_ticket:{ticketId}
	 * TTL: 30秒
	 */
	LOGIN_TICKET("uaa:auth:ticket", Duration.ofSeconds(30), "登录流程临时凭证");

	private final String prefix;
	private final Duration ttl;
	private final String desc;
}
