package io.github.luyang.platform.uaa.common.constant;

import lombok.experimental.UtilityClass;

/**
 * 认证相关常量定义
 *
 * @author yang.lu
 */
@UtilityClass
public class AuthConstant {

	/**
	 * SSO 全局票据 TGC (Ticket Granting Cookie) 的 Cookie 名称
	 * 使用 __Host- 前缀增强安全性，确保 Cookie 仅在当前主机且通过 HTTPS 传输
	 */
	public final static String COOKIE_SSO_TGC = "__Host-SSO_TGC";

	/**
	 * 授权请求接口路径
	 */
	public static final String AUTHORIZE_REQ_PATH = "/auth/authorize";

	/**
	 * 授权请求参数缓存 Key 的查询参数名
	 * 用于登录成功后重定向回授权接口时传递缓存的参数标识
	 */
	public static final String AUTHORIZE_REQ_PARAMS_KEY = "authReqParamKey";

	/**
	 * 授权码（Authorization Code）参数名
	 */
	public static final String PARAM_CODE = "code";

	/**
	 * 防 CSRF 攻击的状态参数名
	 */
	public static final String PARAM_STATE = "state";
}
