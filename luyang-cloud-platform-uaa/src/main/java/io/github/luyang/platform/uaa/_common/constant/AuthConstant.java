package io.github.luyang.platform.uaa._common.constant;

import cn.hutool.core.text.StrPool;

import java.time.Duration;

/**
 * 认证授权相关常量定义
 *
 * @author yang.lu
 */
public class AuthConstant {

	/**
	 * 登录跳转目标参数名
	 */
	public static final String PARAM_TARGET = "target";

	/**
	 * 授权码参数名
	 */
	public static final String PARAM_CODE = "code";

	/**
	 * 防 CSRF 状态参数名
	 */
	public static final String PARAM_STATE = "state";

	/**
	 * Cookie 中存储登录凭证的键名
	 */
	public static final String COOKIE_LOGIN_TICKET = "login_ticket";

	/**
	 * 登录凭证的过期时间
	 */
	public static final Duration LOGIN_TICKET_TTL = Duration.ofHours(1);

	/**
	 * 构建登录凭证的 Redis 键
	 *
	 * @param ticket 登录凭证
	 * @return Redis 键
	 * @author yang.lu
	 */
	public static String buildLoginTicketRedisKey(String ticket) {
		return COOKIE_LOGIN_TICKET + StrPool.COLON + ticket;
	}

	/**
	 * 构建授权码的 Redis 键
	 *
	 * @param code 授权码
	 * @return Redis 键
	 * @author yang.lu
	 */
	public static String buildAuthorizationCodeRedisKey(String code) {
		return "authorization_code:" + code;
	}

	/**
	 * 构建客户端的 Redis 键
	 *
	 * @param clientId 客户端 ID
	 * @return Redis 键
	 * @author yang.lu
	 */
	public static String buildClientRedisKey(String clientId) {
		return "client:" + clientId;
	}
}
