package io.github.luyang.platform.open.client.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * 创建客户端请求 (Request)
 *
 * @param clientName           应用名，非空，最大长度 64
 * @param accessTokenValidity  访问令牌有效期（秒），非空，正整数
 * @param refreshTokenValidity 刷新令牌有效期（秒），非空，正整数
 * @param grantTypes           支持的授权类型，非空
 * @param redirectUris         回调地址，1~5 个，格式为合法 URL
 * @param autoApprove          是否自动授权
 * @param description          应用描述，最多 256 字符
 * @author yang.lu
 */
public record ClientCreateRequest(

	@NotBlank(message = "应用名不能为空")
	@Size(max = 64, message = "应用名不能超过 64 个字符")
	String clientName,

	@Positive(message = "访问令牌有效期必须为正数")
	Integer accessTokenValidity,

	@Positive(message = "刷新令牌有效期必须为正数")
	Integer refreshTokenValidity,

	@NotEmpty(message = "授权类型不能为空")
	List<@NotBlank(message = "授权类型不能为空字符串") String> grantTypes,

	@Size(min = 1, max = 5, message = "回调地址数量必须在 1 到 5 个之间")
	List<@NotBlank(message = "回调地址不能为空") @URL(message = "回调地址必须为合法的 URL") String> redirectUris,

	Boolean autoApprove,

	@Size(max = 256, message = "应用描述不能超过 256 个字符")
	String description
) {
}
