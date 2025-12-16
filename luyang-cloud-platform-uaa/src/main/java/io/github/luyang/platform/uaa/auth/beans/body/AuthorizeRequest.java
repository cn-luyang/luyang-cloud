package io.github.luyang.platform.uaa.auth.beans.body;

import io.github.luyang.platform.uaa._common.enums.CodeChallengeMethodEnum;
import io.github.luyang.starter.base.validation.InEnum;
import io.github.luyang.starter.base.validation.InValues;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

/**
 * 授权请求参数
 *
 * <h4>state - 防止CSRF跨站请求伪造攻击</h4>
 * <ul>
 *   <li><b>工作原理</b>：客户端生成随机字符串，服务端在重定向时原样返回，客户端验证一致性</li>
 *   <li><b>服务端处理</b>：不存储state，仅做透传。如果请求中包含state，重定向URL中必须包含相同的state参数</li>
 *   <li><b>安全目的</b>：确保授权响应来自真实的授权服务器，而非恶意伪造</li>
 * </ul>
 *
 * <h4>nonce - 防止重放攻击并绑定会话</h4>
 * <ul>
 *   <li><b>工作原理</b>：客户端生成随机值，服务端存储并在生成id_token时包含此值</li>
 *   <li><b>服务端处理</b>：
 *     <ul>
 *       <li>授权阶段：将nonce存储到Redis中，与授权会话关联</li>
 *       <li>令牌交换阶段：从Redis取出nonce，将其放入id_token的payload中</li>
 *     </ul>
 *   </li>
 *   <li><b>安全目的</b>：防止攻击者截获id_token后重复使用，确保令牌与特定会话绑定</li>
 * </ul>
 *
 * <h4>code_challenge - 防止授权码截获攻击(PKCE)</h4>
 * <ul>
 *   <li><b>工作原理</b>：客户端生成code_verifier并计算challenge，换token时提交verifier验证</li>
 *   <li><b>服务端处理</b>：
 *     <ul>
 *       <li>授权阶段：校验code_challenge不为空（OAuth 2.1要求），存储challenge和method</li>
 *       <li>令牌交换阶段：客户端提交code + code_verifier，服务端计算verifier的哈希值并与存储的challenge比对</li>
 *     </ul>
 *   </li>
 *   <li><b>安全目的</b>：确保使用授权码换取令牌的就是原始授权请求的发起者，防止授权码被中间人截获滥用</li>
 * </ul>
 *
 * @param clientId            客户端ID
 * @param redirectUri         授权成功后的回调地址
 * @param responseType        响应类型，如"code"表示授权码流程
 * @param scopes              请求的权限范围
 * @param state               防CSRF的随机字符串，由客户端生成
 * @param nonce               防重放攻击的随机值，用于OIDC协议
 * @param codeChallenge       PKCE码，code_verifier的哈希值
 * @param codeChallengeMethod PKCE哈希算法
 * @author yang.lu
 */
public record AuthorizeRequest(

	@NotBlank(message = "客户端 ID 不能为空")
	String clientId,

	@URL(protocol = "http,https", message = "回调地址必须是合法的http或https链接")
	String redirectUri,

	@InValues(values = "code", message = "响应类型必须为code")
	String responseType,

	Set<String> scopes,

	@NotBlank(message = "防CSRF令牌不能为空")
	String state,

	@NotBlank(message = "防重放随机值不能为空")
	String nonce,

	@NotBlank(message = "PKCE码不能为空")
	String codeChallenge,

	@InEnum(value = CodeChallengeMethodEnum.class, message = "PKCE算法类型不正确")
	String codeChallengeMethod
) {
}
