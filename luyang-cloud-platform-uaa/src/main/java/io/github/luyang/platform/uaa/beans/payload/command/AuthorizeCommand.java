package io.github.luyang.platform.uaa.beans.payload.command;

import java.io.Serial;
import java.io.Serializable;

/**
 * 授权请求
 *
 * @param clientId            客户端 ID
 * @param redirectUri         授权成功后的回调地址
 * @param responseType        响应类型，如"code"表示授权码流程
 * @param scope               请求的权限范围
 * @param state               防止 CSRF（跨站请求伪造），由客户端生成，客户端收到重定向回调后，必须校验返回的 state 是否与自己生成的一致
 * @param nonce               防止 id_token 重放。由客户端生成，服务器将其写入 id_token；客户端收到 id_token 后比对 nonce 值，不一致即拒绝
 * @param codeChallenge       PKCE 验证码。客户端生成code_verifier并计算challenge，服务端计算verifier的哈希值并与存储的challenge比对
 * @param codeChallengeMethod PKCE 计算方式
 * @author yang.lu
 */
public record AuthorizeCommand(
	String clientId,
	String redirectUri,
	String responseType,
	String scope,
	String state,
	String nonce,
	String codeChallenge,
	String codeChallengeMethod
) implements Serializable {
	@Serial
	private static final long serialVersionUID = 1941129885226747800L;
}
