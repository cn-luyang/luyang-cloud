package io.github.luyang.platform.open.client.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.List;

/**
 * 更新客户端请求
 *
 * @param clientId             客户端唯一标识，必填，最大长度 64
 * @param clientName           应用名称，可选，最大长度 64
 * @param accessTokenValidity  访问令牌有效期（秒），可选，若传入则必须为正整数
 * @param refreshTokenValidity 刷新令牌有效期（秒），可选，若传入则必须为正整数
 * @param grantTypes           授权类型集合，可选，若传入则至少包含一个元素
 * @param redirectUris         回调地址集合，可选，若传入则数量必须在 1 到 5 个之间，且每个地址必须为合法 URL
 * @param autoApprove          是否自动授权，可选
 * @param description          应用描述，可选，最多 256 字符
 * @author yang.lu
 */
public record ClientUpdateRequest(

	@NotBlank(message = "客户端ID不能为空")
	@Size(max = 64, message = "客户端ID不能超过 64 个字符")
	String clientId,

	@Size(max = 64, message = "应用名称不能超过 64 个字符")
	String clientName,

	@Positive(message = "访问令牌有效期必须为正数")
	Integer accessTokenValidity,

	@Positive(message = "刷新令牌有效期必须为正数")
	Integer refreshTokenValidity,

	List<@NotBlank(message = "授权类型不能为空字符串") String> grantTypes,

	@Size(max = 5, message = "回调地址数量最多 5 个")
	List<@URL(message = "回调地址必须为合法的 URL") String> redirectUris,

	Boolean autoApprove,

	@Size(max = 256, message = "应用描述不能超过 256 个字符")
	String description
) {
}
