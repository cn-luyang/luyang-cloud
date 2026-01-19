package io.github.luyang.platform.uaa.auth.beans.body;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import io.github.luyang.platform.uaa._common.enums.PkceMethodEnum;
import io.github.luyang.starter.base.validation.InEnum;
import io.github.luyang.starter.base.validation.InValues;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 授权请求参数
 *
 * @param clientId            客户端ID
 * @param redirectUri         授权成功后的回调地址
 * @param responseType        响应类型，如"code"表示授权码流程
 * @param scope               请求的权限范围
 * @param state               防止 CSRF（跨站请求伪造），由客户端生成，客户端收到重定向回调后，必须校验返回的 state 是否与自己生成的一致
 * @param nonce               防止 id_token 重放。由客户端生成，服务器将其写入 id_token；客户端收到 id_token 后比对 nonce 值，不一致即拒绝
 * @param codeChallenge       PKCE 验证码。客户端生成code_verifier并计算challenge，服务端计算verifier的哈希值并与存储的challenge比对
 * @param codeChallengeMethod PKCE 计算方式
 * @author yang.lu
 */
public record AuthorizeRequest(

	String loginTicket,

	@NotBlank(message = "客户端 ID 不能为空")
	String clientId,

	@URL(protocol = "http,https", message = "回调地址必须是合法的http或https链接")
	String redirectUri,

	@InValues(values = "code", message = "响应类型必须为code")
	String responseType,

	String scope,

	String state,

	@NotBlank(message = "防重放随机值不能为空")
	String nonce,

	@NotBlank(message = "PKCE 码不能为空")
	String codeChallenge,

	@InEnum(value = PkceMethodEnum.class, message = "PKCE 编码方式类型不正确")
	String codeChallengeMethod
) {

	public Set<String> scopes() {
		if (StrUtil.isBlank(scope)) {
			return Collections.emptySet();
		}

		return StrUtil.split(scope, StrPool.C_SPACE)
			.stream()
			.filter(StrUtil::isNotBlank)
			.collect(Collectors.toSet());
	}
}
