package io.github.luyang.platform.uaa.client.beans.body;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

/**
 * 客户端创建请求参数
 *
 * @param clientName           客户端名称
 * @param accessTokenValidity  访问令牌有效期（秒）
 * @param refreshTokenValidity 刷新令牌有效期（秒）
 * @param grantTypes           授权类型列表
 * @param redirectUris         授权回调地址列表
 * @param scopes               授权范围列表
 * @param description          客户端描述
 * @author yang.lu
 */
public record ClientCreateRequest(

	@NotBlank(message = "客户端名称不能为空")
	@Size(max = 64, message = "客户端名称不能超过 64 个字符")
	String clientName,

	@NotNull(message = "访问令牌有效期不能为空")
	@Positive(message = "访问令牌有效期必须为正整数")
	Integer accessTokenValidity,

	@NotNull(message = "刷新令牌有效期不能为空")
	@Positive(message = "刷新令牌有效期必须为正整数")
	Integer refreshTokenValidity,

	@NotNull(message = "授权类型不能为空")
	@Size(min = 1, message = "至少指定一种授权类型")
	Set<String> grantTypes,

	@Size(min = 1, max = 5, message = "回调地址数量必须在 1 到 5 个之间")
	Set<@NotBlank(message = "回调地址不能为空") @URL(message = "回调地址必须为合法的 URL") String> redirectUris,

	@NotNull(message = "授权范围不能为空")
	@Size(min = 1, message = "至少指定一个授权范围")
	Set<String> scopes,

	@Size(max = 256, message = "客户端描述不能超过 256 个字符")
	String description
) {
}
