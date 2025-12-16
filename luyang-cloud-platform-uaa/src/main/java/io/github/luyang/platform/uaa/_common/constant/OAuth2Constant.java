package io.github.luyang.platform.uaa._common.constant;

import cn.hutool.core.text.StrPool;

import java.time.Duration;

/**
 * OAuth 相关常量
 *
 * @author yang.lu
 */
public class OAuth2Constant {

	/**
	 * 登录跳转目标参数名
	 */
	public static final String PARAM_TARGET = "target";

	/**
	 * 授权码参数名
	 */
	public static final String PARAM_CODE = "code";

	/**
	 * 防CSRF状态参数名
	 */
	public static final String PARAM_STATE = "state";

	/**
	 * Cookie中存储登录凭证的键名
	 */
	public static final String COOKIE_LOGIN_TICKET = "login_ticket";

	/**
	 * Redis中存储登录凭证的前缀，示例: login_ticket:xxx
	 */
	public static final String REDIS_LOGIN_TICKET_KEY_PREFIX = COOKIE_LOGIN_TICKET.concat(StrPool.COLON);

	/**
	 * 登录凭证的过期时间
	 */
	public static final Duration LOGIN_TICKET_TTL = Duration.ofHours(1);
}
