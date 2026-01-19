package io.github.luyang.platform.uaa._common.constant;

/**
 * OAuth2 相关常量定义
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
	 * 防 CSRF 状态参数名
	 */
	public static final String PARAM_STATE = "state";

	public static final String PARAM_LOGIN_TICKET = "login_ticket";

	/**
	 * SSO Session ID 的 Cookie 名称
	 */
	public static final String COOKIE_SSO_SID = "SSO_SID";
}
