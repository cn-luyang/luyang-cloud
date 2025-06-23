package io.github.luyang.platform.open.auth.controller.request;

/**
 * 登录请求 (Request)
 *
 * @param clientId    客户端ID，非空，最大长度 64
 * @param redirectUri 回调地址，非空，格式为合法 URL
 * @param account     用户名/邮箱/手机号，非空
 * @param secret      密码或其他凭证，非空
 * @param loginType   认证类型(密码/短信等)，非空
 * @author yang.lu
 */
public record LoginRequest(
	String clientId,
	String redirectUri,
	String account,
	String secret,
	String loginType
) {
}
