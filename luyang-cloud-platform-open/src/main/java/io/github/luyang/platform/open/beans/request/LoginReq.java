package io.github.luyang.platform.open.beans.request;

/**
 * 登录请求
 *
 * @param clientId    客户端ID，非空，最大长度 64
 * @param redirectUri 回调地址，非空，格式为合法 URL
 * @param account     邮箱/手机号，非空
 * @param credential  密码或其他凭证，非空
 * @param loginType   认证类型(密码/短信等)，非空
 * @author yang.lu
 */
public record LoginReq(
	String clientId,
	String redirectUri,
	String account,
	String credential,
	String loginType
) {
}
